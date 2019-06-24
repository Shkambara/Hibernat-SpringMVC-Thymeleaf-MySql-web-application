package com.example.easynotes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.easynotes.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
	
}
