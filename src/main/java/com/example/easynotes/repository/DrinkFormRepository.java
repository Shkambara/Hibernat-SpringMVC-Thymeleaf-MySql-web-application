package com.example.easynotes.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.easynotes.model.DrinkForm;
import com.example.easynotes.model.State;

public interface DrinkFormRepository extends JpaRepository<DrinkForm, Long> {
	public Optional<DrinkForm> findByState(State state);
}
