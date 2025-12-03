package com.example.springcrudmid;

import com.example.springcrudmid.dto.CountryDto;
import com.example.springcrudmid.service.CountryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
public class CountryServiceTest {

    @Autowired
    private CountryService countryService;

    @Test
    void addAndGetCountryTest() {
        CountryDto newCountryDto = new CountryDto();
        newCountryDto.setNameDto("Test Country");

        countryService.addCountry(newCountryDto);

        List<CountryDto> countries = countryService.getAll();
        CountryDto foundCountry = countries.stream()
                .filter(c -> "Test Country".equals(c.getNameDto()))
                .findFirst()
                .orElse(null);

        Assertions.assertNotNull(foundCountry);
        Assertions.assertNotNull(foundCountry.getIdDto());
        Assertions.assertEquals("Test Country", foundCountry.getNameDto());

        CountryDto fetchedById = countryService.getById(foundCountry.getIdDto());
        Assertions.assertNotNull(fetchedById);
        Assertions.assertEquals(foundCountry.getIdDto(), fetchedById.getIdDto());
    }

    @Test
    void getAllTest() {
        countryService.addCountry(new CountryDto(null, "Kazakhstan"));
        countryService.addCountry(new CountryDto(null, "Germany"));

        List<CountryDto> list = countryService.getAll();

        Assertions.assertNotNull(list);
        Assertions.assertFalse(list.isEmpty());
    }

    @Test
    void deleteByIdTest() {
        CountryDto countryToDelete = new CountryDto();
        countryToDelete.setNameDto("Country To Delete");
        countryService.addCountry(countryToDelete);

        CountryDto addedCountry = countryService.getAll().stream()
                .filter(c -> "Country To Delete".equals(c.getNameDto()))
                .findFirst()
                .orElseThrow(() -> new AssertionError("Country for deletion was not found"));

        Long countryId = addedCountry.getIdDto();

        boolean isDeleted = countryService.deleteById(countryId);

        Assertions.assertTrue(isDeleted);

        CountryDto checkCountry = countryService.getById(countryId);
        Assertions.assertNull(checkCountry);

        boolean notDeleted = countryService.deleteById(-999L);
        Assertions.assertFalse(notDeleted);
    }
}