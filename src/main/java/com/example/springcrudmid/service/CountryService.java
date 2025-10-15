package com.example.springcrudmid.service;

import com.example.springcrudmid.dto.CountryDto;
import com.example.springcrudmid.dto.ProductDto;
import com.example.springcrudmid.repository.CountryRepository;
import com.example.springcrudmid.repository.ProductRepository;

import java.util.List;

public interface CountryService {
    List<CountryDto> getAll();
    CountryDto getById(Long id);
    void addCountry(CountryDto countryDto);
    boolean deleteById(Long id);


}
