package com.example.springcrudmid.service.impl;

import com.example.springcrudmid.dto.ProductDto;
import com.example.springcrudmid.mapper.ProductMapper;
import com.example.springcrudmid.model.Product;
import com.example.springcrudmid.repository.ProductRepository;
import com.example.springcrudmid.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;


    @Override
    public List<ProductDto> getAll() {
        return productMapper.toDtoList(productRepository.findAll());
    }

    @Override
    public ProductDto getById(Long id) {
        return productMapper.toDto(productRepository.findById(id).orElse(null));
    }

    @Override
    public void addProduct(ProductDto productDto) {
        productRepository.save(productMapper.toEntity(productDto));

    }

    @Override
    public void updateById(Long id, ProductDto productDto) {
        Product product = productRepository.findById(id).orElseThrow();
        Product productEntity = productMapper.toEntity(productDto);
        product.setName(productEntity.getName());
        product.setPrice(productEntity.getPrice());
        product.setDescription(productEntity.getDescription());

        product.setCategory(productEntity.getCategory());
        product.setCountries(productEntity.getCountries());

        productRepository.save(product);

    }

    @Override
    public boolean deleteById(Long id) {
        if (!productRepository.existsById(id)){
            return false;
        }
        productRepository.deleteById(id);
        return true;
    }
}
