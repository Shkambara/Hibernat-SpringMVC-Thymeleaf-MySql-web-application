package com.example.easynotes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.easynotes.model.ShoppingOrder;

@Repository
public interface ShoppingOrderRepository extends JpaRepository<ShoppingOrder, Long> {

}
