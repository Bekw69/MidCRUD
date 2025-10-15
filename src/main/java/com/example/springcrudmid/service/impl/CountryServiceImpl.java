package com.example.springcrudmid.service.impl;

import com.example.springcrudmid.dto.CountryDto;
import com.example.springcrudmid.mapper.CountryMapper;
import com.example.springcrudmid.model.Country;
import com.example.springcrudmid.repository.CountryRepository;
import com.example.springcrudmid.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {
    private final CountryRepository countryRepository;
    private final CountryMapper countryMapper;

    @Override
    public List<CountryDto> getAll() {
        return countryMapper.toDtoList(countryRepository.findAll());
    }

    @Override
    public CountryDto getById(Long id) {
        return countryMapper.toDto(countryRepository.findById(id).orElse(null));
    }

    @Override
    public void addCountry(CountryDto countryDto) {
        countryRepository.save(countryMapper.toEntity(countryDto));
    }

    @Override
    public boolean deleteById(Long id) {
        if (!countryRepository.existsById(id)) {
            return false;
        }
        countryRepository.deleteById(id);
        return true;
    }
}