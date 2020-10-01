package lambda.work.javacountries.controllers;

import lambda.work.javacountries.models.Country.Country;

public interface CountryFilter {
    boolean test(Country country);
}
