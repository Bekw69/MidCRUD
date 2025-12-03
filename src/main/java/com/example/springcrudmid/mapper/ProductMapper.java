package com.example.springcrudmid.mapper;

import com.example.springcrudmid.dto.ProductDto;
import com.example.springcrudmid.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CountryMapper.class, CategoryMapper.class})
public interface ProductMapper {

    @Mapping(source = "id", target = "idDto")
    @Mapping(source = "name", target = "nameDto")
    @Mapping(source = "price", target = "priceDto")
    @Mapping(source = "description", target = "descriptionDto")
    @Mapping(source = "category", target = "categoryDto")
    @Mapping(source = "countries", target = "countriesDto")
    ProductDto toDto(Product product);

    @Mapping(source = "idDto", target = "id")
    @Mapping(source = "nameDto", target = "name")
    @Mapping(source = "priceDto", target = "price")
    @Mapping(source = "descriptionDto", target = "description")
    @Mapping(source = "categoryDto", target = "category")
    @Mapping(source = "countriesDto", target = "countries")
    Product toEntity(ProductDto productDto);

    List<ProductDto> toDtoList(List<Product> products);
}