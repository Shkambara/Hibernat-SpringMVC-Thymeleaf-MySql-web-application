package com.example.easynotes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.easynotes.model.ShiftSupervisor;

@Repository
public interface ShiftSupervisorRepository extends JpaRepository<ShiftSupervisor, Long>{

}
