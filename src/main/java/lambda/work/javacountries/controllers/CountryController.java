package lambda.work.javacountries.controllers;

import lambda.work.javacountries.models.Country.Country;
import lambda.work.javacountries.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CountryController {
    @Autowired
    CountryRepository countryRepository;

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

    // http://localhost:5000/names/start/{letter}

    // http://localhost:5000/population/total

    // http://localhost:5000/population/min

    // http://localhost:5000/population/max

    // http://localhost:5000/population/median
}
