package com.example.springcrudmid.repository;

import com.example.springcrudmid.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Long> {
}
