package com.example.fakebank.repository;

import com.example.fakebank.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for Account entity
 * JpaRepository provides basic CRUD operations automatically
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    // Spring Data JPA automatically provides:
    // - save()
    // - findById()
    // - findAll()
    // - deleteById()
    // - etc.
}
