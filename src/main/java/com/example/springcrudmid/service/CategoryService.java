package com.example.springcrudmid.service;

import com.example.springcrudmid.dto.CategoryDto;
import com.example.springcrudmid.dto.CountryDto;
import com.example.springcrudmid.dto.ProductDto;
import com.example.springcrudmid.repository.CountryRepository;
import com.example.springcrudmid.repository.ProductRepository;

import java.util.List;

public interface CategoryService {
    List<CategoryDto> getAll();
    CategoryDto getById(Long id);
    void addCategory(CategoryDto categoryDto);
    boolean deleteById(Long id);


}
