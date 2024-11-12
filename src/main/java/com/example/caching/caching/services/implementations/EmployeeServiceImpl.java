package com.example.caching.caching.services.implementations;

import com.example.caching.caching.dto.EmployeeDto;
import com.example.caching.caching.entities.EmployeeEntity;
import com.example.caching.caching.exceptions.ResourceNotFound;
import com.example.caching.caching.repositories.EmployeeRepository;
import com.example.caching.caching.services.EmployeeService;
import com.example.caching.caching.services.SalaryAccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final ModelMapper modelMapper;
    private final EmployeeRepository employeeRepository;
    private final SalaryAccountService salaryAccountService;
    private final String CACHE_NAME="employees";
    @Override
    @Cacheable(cacheNames = CACHE_NAME,key = "#id")
    public EmployeeDto getEmployeeId(Long id) {
        log.info("Fetching employee with id: {}", id);
        EmployeeEntity employee = employeeRepository
                .findById(id)
                .orElseThrow(()-> {
                    log.error("Employee not found with id: {}", id);
                    return new ResourceNotFound("Employee not found!");
                });
        log.info("Successfully fetched employee with id: {}", id);
        return modelMapper.map(employee,EmployeeDto.class);
    }

    @Override
    @Cacheable(cacheNames = CACHE_NAME,key = "'allEmployees'")
    public List<EmployeeDto> getAllEmployees() {
        log.info("Fetching employees!");
        List<EmployeeEntity> employees = employeeRepository.findAll();
        log.info("Successfully fetched employees!");
        return employees
                .stream()
                .map(employeeEntity -> modelMapper.map(employeeEntity,EmployeeDto.class))
                .collect(Collectors.toList());
    }

    @Override
    @CachePut(cacheNames = CACHE_NAME,key = "#result.id")
    @Transactional
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        List<EmployeeEntity> employee = employeeRepository.findByEmail(employeeDto.getEmail());
        if(!employee.isEmpty()){
            throw new RuntimeException("Employee with this email already present");
        }
        EmployeeEntity employeeEntity = modelMapper.map(employeeDto,EmployeeEntity.class);
        log.info("Saving Employee.....");
        EmployeeEntity savedEmployee = employeeRepository.save(employeeEntity);
        log.info("Employee saved!!");
        salaryAccountService.createAccount(savedEmployee);
        log.info("Salary account created!!");
        return modelMapper.map(savedEmployee,EmployeeDto.class);
    }

    @Override
    @CachePut(cacheNames = CACHE_NAME,key = "#id")
    public EmployeeDto updateEmployee(Long id, EmployeeDto employeeDto) {
        EmployeeEntity employee = employeeRepository
                .findById(id)
                .orElseThrow(()->{
                    log.info("No employee found!!");
                    return new ResourceNotFound("Employee not found!");
                });
        if(!employeeDto.getEmail().equals(employee.getEmail())){
            log.error("Attempted to update the email!!");
            throw new RuntimeException("Email cannot be updated!!");
        }
        EmployeeEntity toBeUpdatedEmployee = modelMapper.map(employeeDto,EmployeeEntity.class);
        toBeUpdatedEmployee.setId(id);
        EmployeeEntity savedEmployee = employeeRepository.save(toBeUpdatedEmployee);
        return modelMapper.map(savedEmployee,EmployeeDto.class);
    }

    @Override
    @CacheEvict(cacheNames = CACHE_NAME,key = "#id")
    public void deleteEmployee(Long id) {
        Optional<EmployeeEntity> employee = employeeRepository.findById(id);
        if(employee.isEmpty()){
            log.error("No Employee found with id: {}", id);
            throw new ResourceNotFound("No employee found");
        }
        employeeRepository.deleteById(id);
        log.info("Employee removed!!");
    }
}
