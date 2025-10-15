package com.example.springcrudmid.repository;

import com.example.springcrudmid.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
