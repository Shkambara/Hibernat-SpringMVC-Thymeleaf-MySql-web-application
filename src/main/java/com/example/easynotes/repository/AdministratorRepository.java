package com.example.easynotes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.easynotes.model.Administrator;

@Repository
public interface AdministratorRepository extends JpaRepository<Administrator, Long> {

}
