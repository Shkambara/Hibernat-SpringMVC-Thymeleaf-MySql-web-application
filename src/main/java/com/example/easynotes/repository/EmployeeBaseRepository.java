package com.example.easynotes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.example.easynotes.model.Employee;

@NoRepositoryBean
public interface EmployeeBaseRepository <T extends Employee> extends JpaRepository<T, Long>{
	public T findBySpecialId(String id);
}