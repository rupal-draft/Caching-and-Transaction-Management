package com.example.caching.caching.controllers;

import com.example.caching.caching.dto.EmployeeDto;
import com.example.caching.caching.dto.SalaryAccountDto;
import com.example.caching.caching.services.EmployeeService;
import com.example.caching.caching.services.SalaryAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final SalaryAccountService salaryAccountService;

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable Long id){
        EmployeeDto employee = employeeService.getEmployeeId(id);
        return ResponseEntity.ok(employee);
    }
    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getAllEmployees(){
        List<EmployeeDto>employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }
    @PostMapping
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto){
        EmployeeDto createdEmployee = employeeService.createEmployee(employeeDto);
        return new ResponseEntity<>(createdEmployee, HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id){
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@RequestBody EmployeeDto employeeDto,@PathVariable Long id){
        EmployeeDto updatedEmployee = employeeService.updateEmployee(id,employeeDto);
        return ResponseEntity.ok(updatedEmployee);
    }
    @PutMapping("/salary/{accountId}")
    public ResponseEntity<SalaryAccountDto> incrementSalary(@PathVariable Long accountId){
        SalaryAccountDto salaryAccountDto = salaryAccountService.incrementSalary(accountId);
        return ResponseEntity.ok(salaryAccountDto);
    }
}
