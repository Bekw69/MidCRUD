package com.example.springcrudmid.mapper;

import com.example.springcrudmid.dto.CategoryDto;
import com.example.springcrudmid.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    @Mapping(source = "id", target = "idDto")
    @Mapping(source = "name", target = "nameDto")
    CategoryDto toDto(Category category);

    @Mapping(source = "idDto", target = "id")
    @Mapping(source = "nameDto", target = "name")
    Category toEntity(CategoryDto categoryDto);

    List<CategoryDto> toDtoList(List<Category> categories);
}