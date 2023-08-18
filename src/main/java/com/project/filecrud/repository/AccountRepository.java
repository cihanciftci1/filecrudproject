package com.project.filecrud.repository;

import com.project.filecrud.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    Optional<Account> findByUsername(String username);
    Boolean existsByUsername(final String username);
}
