package lambda.work.javacountries.controllers;

import lambda.work.javacountries.models.Country.Country;
import lambda.work.javacountries.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CountryController {
    @Autowired
    CountryRepository countryRepository;

    private List<Country> filterCountries(List<Country> countries, CountryFilter filter)
    {
        List<Country> result = new ArrayList<>();

        for(Country country : countries)
        {
            if(filter.test(country)) result.add(country);
        }

        return result;
    }

    // http://localhost:5000/names/all
    @GetMapping(value = "/names/all", produces = {"application/json"})
    public ResponseEntity<?> getAllNames(){
        List<Country> countries = new ArrayList<>();
        List<String> result = new ArrayList<>();

        countryRepository.findAll().iterator().forEachRemaining(countries::add);

        for(Country c : countries)
        {
            result.add(c.getName());
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // http://localhost:5000/names/start/{initial}
    @GetMapping(value = "/names/start/{initial}", produces = {"application/json"})
    public ResponseEntity<?> getNamesByInitial(@PathVariable char initial){
        List<Country> countries = new ArrayList<>();
        List<String> result = new ArrayList<>();

        countryRepository.findAll().iterator().forEachRemaining(countries::add);

        countries = filterCountries(countries, c -> c.getName().charAt(0) == initial);

        for(Country c : countries)
        {
            result.add(c.getName());
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // http://localhost:5000/population/total

    // http://localhost:5000/population/min

    // http://localhost:5000/population/max

    // http://localhost:5000/population/median
}
