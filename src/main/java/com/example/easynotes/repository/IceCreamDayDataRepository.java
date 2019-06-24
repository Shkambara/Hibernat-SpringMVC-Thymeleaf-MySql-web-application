package com.example.easynotes.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;



import com.example.easynotes.model.IceCreamDayData;

public interface IceCreamDayDataRepository extends JpaRepository<IceCreamDayData, Long>{
	public Optional<IceCreamDayData> findByDate(LocalDate date);
	
}
