package com.example.caching.caching.services;

import com.example.caching.caching.dto.SalaryAccountDto;
import com.example.caching.caching.entities.EmployeeEntity;

public interface SalaryAccountService {
    void createAccount(EmployeeEntity employeeDto);
    SalaryAccountDto incrementSalary(Long id);
}
