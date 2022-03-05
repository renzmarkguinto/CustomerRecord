package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Savings;

public interface SavingsRepository extends JpaRepository<Savings, Long> {

}
