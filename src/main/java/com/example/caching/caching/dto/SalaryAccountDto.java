package com.example.caching.caching.dto;

import com.example.caching.caching.entities.EmployeeEntity;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class SalaryAccountDto {
    private Long id;
    private BigDecimal balance;
    private EmployeeEntity employeeEntity;
}
