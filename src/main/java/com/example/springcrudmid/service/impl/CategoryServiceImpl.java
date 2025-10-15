package com.example.springcrudmid.service.impl;

import com.example.springcrudmid.dto.CategoryDto;
import com.example.springcrudmid.dto.CountryDto;
import com.example.springcrudmid.mapper.CategoryMapper;
import com.example.springcrudmid.model.Category;
import com.example.springcrudmid.repository.CategoryRepository;
import com.example.springcrudmid.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public List<CategoryDto> getAll() {
        return categoryMapper.toDtoList(categoryRepository.findAll());
    }

    @Override
    public CategoryDto getById(Long id) {
        return categoryMapper.toDto(categoryRepository.findById(id).orElse(null));
    }


    @Override
    public void addCategory(CategoryDto categoryDto) {
        categoryRepository.save(categoryMapper.toEntity(categoryDto));
    }

    @Override
    public boolean deleteById(Long id) {
        if (!categoryRepository.existsById(id)) {
            return false;
        }
        categoryRepository.deleteById(id);
        return true;
    }
}