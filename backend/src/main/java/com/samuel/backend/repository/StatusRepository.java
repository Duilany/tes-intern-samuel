package com.samuel.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.samuel.backend.entity.Status;

public interface StatusRepository extends JpaRepository<Status, Integer> {
}