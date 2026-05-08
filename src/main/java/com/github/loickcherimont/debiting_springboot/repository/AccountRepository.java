package com.github.loickcherimont.debiting_springboot.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.loickcherimont.debiting_springboot.models.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, UUID> {}
