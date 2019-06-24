package com.example.easynotes.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.easynotes.model.IceCreamForm;
import com.example.easynotes.model.State;

public interface IceCreamFormRepository extends JpaRepository<IceCreamForm, Long>{
	public Optional<IceCreamForm> findByState(State state);
}
