package com.example.caching.caching.repositories;


import com.example.caching.caching.entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity,Long> {
    List<EmployeeEntity> findByEmail(String email);
}
