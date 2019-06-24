package com.example.easynotes.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.easynotes.model.DrinkDayData;

public interface DrinkDayDataRepository extends JpaRepository<DrinkDayData, Long> {
	public Optional<DrinkDayData> findByDate(LocalDate date);
}
