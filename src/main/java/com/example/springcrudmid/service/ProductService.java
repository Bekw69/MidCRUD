package com.example.springcrudmid.service;

import com.example.springcrudmid.dto.ProductDto;

import java.util.List;

public interface ProductService {
    List<ProductDto> getAll();
    ProductDto getById(Long id);
    void addProduct(ProductDto productDto);
    void updateById(Long id, ProductDto productDto);
    boolean deleteById(Long id);

}
