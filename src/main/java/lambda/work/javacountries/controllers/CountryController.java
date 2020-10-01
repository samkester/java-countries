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
    @GetMapping(value = "/population/total", produces = {"application/json"})
    public ResponseEntity<?> getPopulationTotal(){
        List<Country> countries = new ArrayList<>();
        long result = 0;

        countryRepository.findAll().iterator().forEachRemaining(countries::add);

        for(Country c : countries)
        {
            result += c.getPopulation();
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // http://localhost:5000/population/min
    @GetMapping(value = "/population/min", produces = {"application/json"})
    public ResponseEntity<?> getPopulationMin(){
        List<Country> countries = new ArrayList<>();

        countryRepository.findAll().iterator().forEachRemaining(countries::add);

        countries.sort((first, second) -> (int)(first.getPopulation() - second.getPopulation()));

        return new ResponseEntity<>(countries.get(0), HttpStatus.OK);
    }

    // http://localhost:5000/population/max
    @GetMapping(value = "/population/max", produces = {"application/json"})
    public ResponseEntity<?> getPopulationMax(){
        List<Country> countries = new ArrayList<>();

        countryRepository.findAll().iterator().forEachRemaining(countries::add);

        countries.sort((first, second) -> (int)(second.getPopulation() - first.getPopulation()));

        return new ResponseEntity<>(countries.get(0), HttpStatus.OK);
    }

    // http://localhost:5000/population/median
    @GetMapping(value = "/population/median", produces = {"application/json"})
    public ResponseEntity<?> getPopulationMedian(){
        List<Country> countries = new ArrayList<>();

        countryRepository.findAll().iterator().forEachRemaining(countries::add);

        countries.sort((first, second) -> (int)(first.getPopulation() - second.getPopulation()));

        return new ResponseEntity<>(countries.get(countries.size()/2), HttpStatus.OK);
    }
}
