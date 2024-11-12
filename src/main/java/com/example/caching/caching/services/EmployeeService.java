package com.example.caching.caching.services;

import com.example.caching.caching.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {
    EmployeeDto getEmployeeId(Long id);
    List<EmployeeDto> getAllEmployees();
    EmployeeDto createEmployee(EmployeeDto employeeDto);
    EmployeeDto updateEmployee(Long id,EmployeeDto employeeDto);
    void deleteEmployee(Long id);
}
