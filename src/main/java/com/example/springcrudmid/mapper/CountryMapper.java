package com.example.springcrudmid.mapper;

import com.example.springcrudmid.dto.CountryDto;
import com.example.springcrudmid.model.Country;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CountryMapper {

    @Mapping(source = "id", target = "idDto")
    @Mapping(source = "name", target = "nameDto")
    CountryDto toDto(Country country);

    @Mapping(source = "idDto", target = "id")
    @Mapping(source = "nameDto", target = "name")
    Country toEntity(CountryDto countryDto);

    List<CountryDto> toDtoList(List<Country> countries);
}