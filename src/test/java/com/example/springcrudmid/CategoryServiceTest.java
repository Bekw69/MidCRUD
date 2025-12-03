package com.example.springcrudmid;

import com.example.springcrudmid.dto.CategoryDto;
import com.example.springcrudmid.service.CategoryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
public class CategoryServiceTest {

    @Autowired
    private CategoryService categoryService;

    @Test
    void addAndGetCategoryTest() {
        CategoryDto newCategoryDto = new CategoryDto();
        newCategoryDto.setNameDto("Test Category");

        categoryService.addCategory(newCategoryDto);

        List<CategoryDto> categories = categoryService.getAll();
        CategoryDto foundCategory = categories.stream()
                .filter(c -> "Test Category".equals(c.getNameDto()))
                .findFirst()
                .orElse(null);

        Assertions.assertNotNull(foundCategory);
        Assertions.assertNotNull(foundCategory.getIdDto());
        Assertions.assertEquals("Test Category", foundCategory.getNameDto());

        CategoryDto fetchedById = categoryService.getById(foundCategory.getIdDto());
        Assertions.assertNotNull(fetchedById);
        Assertions.assertEquals(foundCategory.getIdDto(), fetchedById.getIdDto());
    }

    @Test
    void getAllTest() {
        categoryService.addCategory(new CategoryDto(null, "Electronics"));
        categoryService.addCategory(new CategoryDto(null, "Books"));

        List<CategoryDto> list = categoryService.getAll();

        Assertions.assertNotNull(list);
        Assertions.assertFalse(list.isEmpty());
    }


    @Test
    void deleteByIdTest() {
        CategoryDto categoryToDelete = new CategoryDto();
        categoryToDelete.setNameDto("Category To Delete");
        categoryService.addCategory(categoryToDelete);

        CategoryDto addedCategory = categoryService.getAll().stream()
                .filter(c -> "Category To Delete".equals(c.getNameDto()))
                .findFirst()
                .orElseThrow(() -> new AssertionError("Category for deletion was not found"));

        Long categoryId = addedCategory.getIdDto();

        boolean isDeleted = categoryService.deleteById(categoryId);

        Assertions.assertTrue(isDeleted);

        CategoryDto checkCategory = categoryService.getById(categoryId);
        Assertions.assertNull(checkCategory);

        boolean notDeleted = categoryService.deleteById(-999L);
        Assertions.assertFalse(notDeleted);
    }
}