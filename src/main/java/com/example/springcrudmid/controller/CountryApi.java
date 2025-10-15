package com.example.springcrudmid.controller;

import com.example.springcrudmid.dto.CountryDto;
import com.example.springcrudmid.service.impl.CountryServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/country")
@RequiredArgsConstructor
public class CountryApi {
    @Autowired
    private final CountryServiceImpl countryService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(countryService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(countryService.getById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addCountry(@RequestBody CountryDto countryDto) {
        countryService.addCountry(countryDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCountry(@PathVariable(name = "id") Long id) {
        if (countryService.deleteById(id)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}