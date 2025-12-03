package com.example.springcrudmid;

import com.example.springcrudmid.dto.CategoryDto;
import com.example.springcrudmid.dto.CountryDto;
import com.example.springcrudmid.dto.ProductDto;
import com.example.springcrudmid.service.CategoryService;
import com.example.springcrudmid.service.CountryService;
import com.example.springcrudmid.service.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
public class ProductServiceTest {

    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CountryService countryService;

    private CategoryDto testCategory;
    private CountryDto testCountry1;
    private CountryDto testCountry2;

    @BeforeEach
    void setUp() {
        categoryService.addCategory(new CategoryDto(null, "Test Category"));
        countryService.addCountry(new CountryDto(null, "Test Country 1"));
        countryService.addCountry(new CountryDto(null, "Test Country 2"));

        testCategory = categoryService.getAll().stream().findFirst().orElseThrow();
        List<CountryDto> countries = countryService.getAll();
        testCountry1 = countries.get(0);
        testCountry2 = countries.get(1);
    }

    @Test
    void addAndGetProductTest() {
        ProductDto newProduct = new ProductDto();
        newProduct.setNameDto("New Laptop");
        newProduct.setPriceDto(500000.0);
        newProduct.setDescriptionDto("A powerful new laptop");
        newProduct.setCategoryDto(testCategory);
        newProduct.setCountriesDto(List.of(testCountry1));

        productService.addProduct(newProduct);

        ProductDto foundProduct = productService.getAll().stream()
                .filter(p -> "New Laptop".equals(p.getNameDto()))
                .findFirst()
                .orElse(null);

        Assertions.assertNotNull(foundProduct);
        Assertions.assertEquals("New Laptop", foundProduct.getNameDto());
        Assertions.assertEquals(500000.0, foundProduct.getPriceDto());
        Assertions.assertEquals(testCategory.getIdDto(), foundProduct.getCategoryDto().getIdDto());
        Assertions.assertEquals(1, foundProduct.getCountriesDto().size());
        Assertions.assertEquals(testCountry1.getNameDto(), foundProduct.getCountriesDto().get(0).getNameDto());
    }

    @Test
    void updateProductTest() {
        ProductDto initialProduct = new ProductDto();
        initialProduct.setNameDto("Old Phone");
        initialProduct.setPriceDto(150000.0);
        initialProduct.setCategoryDto(testCategory);
        initialProduct.setCountriesDto(List.of(testCountry1));
        productService.addProduct(initialProduct);

        ProductDto productToUpdate = productService.getAll().stream()
                .filter(p -> "Old Phone".equals(p.getNameDto()))
                .findFirst()
                .orElseThrow();

        Long productId = productToUpdate.getIdDto();

        ProductDto updatedInfo = new ProductDto();
        updatedInfo.setNameDto("New Shiny Phone");
        updatedInfo.setPriceDto(250000.0);
        updatedInfo.setDescriptionDto("Updated description");
        updatedInfo.setCategoryDto(testCategory);
        updatedInfo.setCountriesDto(List.of(testCountry1, testCountry2));

        productService.updateById(productId, updatedInfo);

        ProductDto updatedProduct = productService.getById(productId);
        Assertions.assertNotNull(updatedProduct);
        Assertions.assertEquals("New Shiny Phone", updatedProduct.getNameDto());
        Assertions.assertEquals(250000.0, updatedProduct.getPriceDto());
        Assertions.assertEquals(2, updatedProduct.getCountriesDto().size());
    }

    @Test
    void deleteProductTest() {
        ProductDto productDto = new ProductDto();
        productDto.setNameDto("Product To Delete");
        productDto.setPriceDto(100.0);
        productDto.setCategoryDto(testCategory);
        productDto.setCountriesDto(List.of(testCountry1));
        productService.addProduct(productDto);

        ProductDto addedProduct = productService.getAll().stream()
                .filter(p -> "Product To Delete".equals(p.getNameDto()))
                .findFirst()
                .orElseThrow();
        Long productId = addedProduct.getIdDto();

        boolean isDeleted = productService.deleteById(productId);

        Assertions.assertTrue(isDeleted);
        Assertions.assertNull(productService.getById(productId));
    }
}