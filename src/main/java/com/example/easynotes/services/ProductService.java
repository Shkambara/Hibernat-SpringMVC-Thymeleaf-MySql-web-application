package com.example.easynotes.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.easynotes.exception.ResourceNotFoundException;
import com.example.easynotes.model.Product;
import com.example.easynotes.repository.ProductRepository;

public class ProductService {
	@Autowired
	ProductRepository productRepository;
	
	public List<Product> getProducts(){
		return productRepository.findAll();
	}
	
	public Optional<Product> getProductById(Long id){
		if(!productRepository.existsById(id)) {
			throw new ResourceNotFoundException("Product", "id", id);

		}
		return productRepository.findById(id);
	}
	
	public Product createProduct(Product product) {
		return productRepository.save(product);
	}
}
