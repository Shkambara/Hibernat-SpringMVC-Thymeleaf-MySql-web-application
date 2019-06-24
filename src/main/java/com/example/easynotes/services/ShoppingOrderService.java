package com.example.easynotes.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.ResourceAccessException;

import com.example.easynotes.model.ShoppingOrder;
import com.example.easynotes.repository.ShoppingOrderRepository;

public class ShoppingOrderService {
	
	@Autowired
	ShoppingOrderRepository shoppingOrderRepository;
	
	public List<ShoppingOrder> getShoppingOrders(){
		return shoppingOrderRepository.findAll();
	}
	
	public Optional<ShoppingOrder> getShoppingOrderById(Long id){
		if(!shoppingOrderRepository.existsById(id)) {
			throw new ResourceAccessException("ShoppingOrder with id " + id + " not found");

		}
		return shoppingOrderRepository.findById(id);
	}
	
	public ShoppingOrder createShoppingOrder(ShoppingOrder shoppingOrder) {
		return shoppingOrderRepository.save(shoppingOrder);
	}
}
