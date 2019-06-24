package com.example.easynotes.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.easynotes.model.EndOfTheDay;
import com.example.easynotes.model.State;

public interface EndOfTheDayRepository extends JpaRepository<EndOfTheDay, Long>{
	public Optional<EndOfTheDay> findByState(State state);
}
