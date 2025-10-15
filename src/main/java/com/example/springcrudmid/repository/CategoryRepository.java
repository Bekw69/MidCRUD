package com.example.springcrudmid.repository;

import com.example.springcrudmid.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
