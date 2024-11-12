package com.example.caching.caching.services.implementations;

import com.example.caching.caching.dto.SalaryAccountDto;
import com.example.caching.caching.entities.EmployeeEntity;
import com.example.caching.caching.entities.SalaryAccountEntity;
import com.example.caching.caching.exceptions.ResourceNotFound;
import com.example.caching.caching.repositories.SalaryAccountRepository;
import com.example.caching.caching.services.SalaryAccountService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.REQUIRED)
public class SalaryAccountServiceImpl implements SalaryAccountService {

    private final SalaryAccountRepository salaryAccountRepository;
    private final ModelMapper modelMapper;
    @Override
    public void createAccount(EmployeeEntity employeeEntity) {
        SalaryAccountEntity salaryAccountEntity = SalaryAccountEntity
                .builder()
                .balance(BigDecimal.ZERO)
                .employee(employeeEntity)
                .build();
        salaryAccountRepository.save(salaryAccountEntity);
    }

    @Override
    public SalaryAccountDto incrementSalary(Long id) {
        SalaryAccountEntity salaryAccountEntity = salaryAccountRepository
                .findById(id)
                .orElseThrow(()-> new ResourceNotFound("No salary account found!"));

        BigDecimal prevBalance = salaryAccountEntity.getBalance();
        BigDecimal newBalance = prevBalance.add(BigDecimal.valueOf(500L));
        salaryAccountEntity.setBalance(newBalance);
        SalaryAccountEntity savedSalaryAccount = salaryAccountRepository.save(salaryAccountEntity);
        return modelMapper.map(savedSalaryAccount,SalaryAccountDto.class);
    }
}
