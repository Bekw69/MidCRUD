package com.example.springcrudmid.dto;

import com.example.springcrudmid.model.Category;
import com.example.springcrudmid.model.Country;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private Long idDto;
    private String nameDto;
    private double priceDto;
    private String descriptionDto;
    private CategoryDto categoryDto;
    private List<CountryDto> countriesDto;
}