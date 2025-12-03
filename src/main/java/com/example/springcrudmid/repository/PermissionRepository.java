package com.example.springcrudmid.repository;

import com.example.springcrudmid.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
    Permission findByRole(String role);
}
