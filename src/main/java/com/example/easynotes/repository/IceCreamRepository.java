package com.example.easynotes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.easynotes.model.IceCream;

@Repository
public interface IceCreamRepository extends JpaRepository<IceCream, Long> {
	
}
