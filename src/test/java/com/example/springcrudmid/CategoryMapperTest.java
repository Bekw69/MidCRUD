package com.example.springcrudmid;

import com.example.springcrudmid.dto.CategoryDto;
import com.example.springcrudmid.mapper.CategoryMapper;
import com.example.springcrudmid.model.Category;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class CategoryMapperTest {

    @Autowired
    private CategoryMapper categoryMapper;

    @Test
    void convertEntityToDtoTest() {
        Category entity = new Category(1L, "Electronics");

        CategoryDto dto = categoryMapper.toDto(entity);

        Assertions.assertNotNull(dto);
        Assertions.assertEquals(entity.getId(), dto.getIdDto());
        Assertions.assertEquals(entity.getName(), dto.getNameDto());
    }

    @Test
    void convertDtoToEntityTest() {
        CategoryDto dto = new CategoryDto(1L, "Electronics");

        Category entity = categoryMapper.toEntity(dto);

        Assertions.assertNotNull(entity);
        Assertions.assertEquals(dto.getIdDto(), entity.getId());
        Assertions.assertEquals(dto.getNameDto(), entity.getName());
    }

    @Test
    void convertEntityListToDtoListTest() {
        List<Category> entityList = new ArrayList<>();
        entityList.add(new Category(1L, "Electronics"));
        entityList.add(new Category(2L, "Books"));

        List<CategoryDto> dtoList = categoryMapper.toDtoList(entityList);

        Assertions.assertNotNull(dtoList);
        Assertions.assertEquals(2, dtoList.size());
        Assertions.assertEquals(entityList.get(0).getName(), dtoList.get(0).getNameDto());
        Assertions.assertEquals(entityList.get(1).getId(), dtoList.get(1).getIdDto());
    }
}
