package lambda.work.javacountries.repositories;

import lambda.work.javacountries.models.Country.Country;
import org.springframework.data.repository.CrudRepository;

public interface CountryRepository extends CrudRepository<Country, Long> {
}
