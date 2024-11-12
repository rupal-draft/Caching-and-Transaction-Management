package com.example.caching.caching.repositories;

import com.example.caching.caching.entities.SalaryAccountEntity;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SalaryAccountRepository extends CrudRepository<SalaryAccountEntity,Long> {
    @Override
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<SalaryAccountEntity> findById(Long id);
}
