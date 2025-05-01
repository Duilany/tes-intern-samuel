package com.samuel.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.samuel.backend.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}