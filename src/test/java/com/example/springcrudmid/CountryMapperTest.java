package com.example.springcrudmid;

import com.example.springcrudmid.dto.CountryDto;
import com.example.springcrudmid.mapper.CountryMapper;
import com.example.springcrudmid.model.Country;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class CountryMapperTest {

    @Autowired
    private CountryMapper countryMapper;

    @Test
    void convertEntityToDtoTest() {
        Country entity = new Country(1L, "Kazakhstan");

        CountryDto dto = countryMapper.toDto(entity);

        Assertions.assertNotNull(dto);
        Assertions.assertEquals(entity.getId(), dto.getIdDto());
        Assertions.assertEquals(entity.getName(), dto.getNameDto());
    }

    @Test
    void convertDtoToEntityTest() {
        CountryDto dto = new CountryDto(1L, "Kazakhstan");

        Country entity = countryMapper.toEntity(dto);

        Assertions.assertNotNull(entity);
        Assertions.assertEquals(dto.getIdDto(), entity.getId());
        Assertions.assertEquals(dto.getNameDto(), entity.getName());
    }

    @Test
    void convertEntityListToDtoListTest() {
        List<Country> entityList = new ArrayList<>();
        entityList.add(new Country(1L, "Kazakhstan"));
        entityList.add(new Country(2L, "USA"));

        List<CountryDto> dtoList = countryMapper.toDtoList(entityList);

        Assertions.assertNotNull(dtoList);
        Assertions.assertEquals(2, dtoList.size());
        Assertions.assertEquals(entityList.get(0).getName(), dtoList.get(0).getNameDto());
        Assertions.assertEquals(entityList.get(1).getId(), dtoList.get(1).getIdDto());
    }
}