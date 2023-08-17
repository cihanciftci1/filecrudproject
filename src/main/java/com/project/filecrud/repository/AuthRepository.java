package com.project.filecrud.repository;

import com.project.filecrud.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRepository extends JpaRepository<Account, Integer> {
}
