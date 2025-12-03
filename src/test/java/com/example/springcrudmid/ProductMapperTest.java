package com.example.springcrudmid;

import com.example.springcrudmid.dto.CategoryDto;
import com.example.springcrudmid.dto.CountryDto;
import com.example.springcrudmid.dto.ProductDto;
import com.example.springcrudmid.mapper.ProductMapper;
import com.example.springcrudmid.model.Category;
import com.example.springcrudmid.model.Country;
import com.example.springcrudmid.model.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.ArrayList;

@SpringBootTest
public class ProductMapperTest {

    @Autowired
    private ProductMapper productMapper;

    @Test
    void convertEntityToDtoTest(){
        Category category = new Category(1L, "Electronics");
        List<Country> countries = new ArrayList<>();
        countries.add(new Country(1L, "China"));
        countries.add(new Country(2L, "USA"));

        Product entity = new Product(10L, "Laptop", 450000.0, "Powerful gaming laptop", category, countries);

        ProductDto dto = productMapper.toDto(entity);

        Assertions.assertNotNull(dto);
        Assertions.assertEquals(entity.getId(), dto.getIdDto());
        Assertions.assertEquals(entity.getName(), dto.getNameDto());
        Assertions.assertEquals(entity.getPrice(), dto.getPriceDto());
        Assertions.assertEquals(entity.getDescription(), dto.getDescriptionDto());

        Assertions.assertNotNull(dto.getCategoryDto());
        Assertions.assertEquals(entity.getCategory().getId(), dto.getCategoryDto().getIdDto());
        Assertions.assertEquals(entity.getCategory().getName(), dto.getCategoryDto().getNameDto());

        Assertions.assertNotNull(dto.getCountriesDto());
        Assertions.assertEquals(entity.getCountries().size(), dto.getCountriesDto().size());
        Assertions.assertEquals(entity.getCountries().get(0).getName(), dto.getCountriesDto().get(0).getNameDto());
    }

    @Test
    void convertDtoToEntityTest(){
        CategoryDto categoryDto = new CategoryDto(1L, "Electronics");
        List<CountryDto> countryDtos = new ArrayList<>();
        countryDtos.add(new CountryDto(1L, "China"));
        countryDtos.add(new CountryDto(2L, "USA"));

        ProductDto dto = new ProductDto(10L, "Laptop", 450000.0, "Powerful gaming laptop", categoryDto, countryDtos);

        Product entity = productMapper.toEntity(dto);

        Assertions.assertNotNull(entity);
        Assertions.assertEquals(dto.getIdDto(), entity.getId());
        Assertions.assertEquals(dto.getNameDto(), entity.getName());
        Assertions.assertEquals(dto.getPriceDto(), entity.getPrice());
        Assertions.assertEquals(dto.getDescriptionDto(), entity.getDescription());

        Assertions.assertNotNull(entity.getCategory());
        Assertions.assertEquals(dto.getCategoryDto().getIdDto(), entity.getCategory().getId());
        Assertions.assertEquals(dto.getCategoryDto().getNameDto(), entity.getCategory().getName());

        Assertions.assertNotNull(entity.getCountries());
        Assertions.assertEquals(dto.getCountriesDto().size(), entity.getCountries().size());
        Assertions.assertEquals(dto.getCountriesDto().get(0).getNameDto(), entity.getCountries().get(0).getName());
    }

    @Test
    void convertEntityListToDtoListTest(){
        Category category1 = new Category(1L, "Electronics");
        List<Country> countries1 = List.of(new Country(1L, "China"));
        Product product1 = new Product(1L, "Laptop", 500000.0, "Work laptop", category1, countries1);

        Category category2 = new Category(2L, "Books");
        List<Country> countries2 = List.of(new Country(2L, "UK"));
        Product product2 = new Product(2L, "Fantasy Book", 15000.0, "A great book", category2, countries2);

        List<Product> entityList = List.of(product1, product2);

        List<ProductDto> dtoList = productMapper.toDtoList(entityList);

        Assertions.assertNotNull(dtoList);
        Assertions.assertEquals(2, dtoList.size());

        Assertions.assertEquals(product1.getName(), dtoList.get(0).getNameDto());
        Assertions.assertEquals(product1.getCategory().getName(), dtoList.get(0).getCategoryDto().getNameDto());
        Assertions.assertEquals(product1.getCountries().size(), dtoList.get(0).getCountriesDto().size());
    }
}