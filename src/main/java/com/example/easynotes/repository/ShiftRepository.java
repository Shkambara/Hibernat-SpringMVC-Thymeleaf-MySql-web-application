package com.example.easynotes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.easynotes.model.Shift;

@Repository
public interface ShiftRepository extends JpaRepository<Shift, Long> {
	
}
