package com.example.easynotes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.easynotes.model.CustomerServiceEmployee;

@Repository
public interface CustomerServiceEmployeeRepository extends JpaRepository<CustomerServiceEmployee, Long>{

}