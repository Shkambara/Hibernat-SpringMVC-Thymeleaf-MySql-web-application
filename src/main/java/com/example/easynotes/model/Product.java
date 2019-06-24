package com.example.easynotes.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name = "Product")
public class Product{

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name = "id", updatable = false, nullable = false)
	private int Id;
	
	@NotNull
	private double price;

	@NotBlank
	private String name;
	
	private String img;
	
	public Product(int id,double price,String name, String img) {
		this.price = price;
		this.name = name;
		this.img = img;
		
	}
	
	@ManyToOne(
	          fetch = FetchType.LAZY,
	          optional = false
	  )
	  @JoinColumn(
	          name = "shop_id"
	  )
	@JsonIgnore
	private ShoppingOrder shoppingOrder;
	
	public void setId(int Id) {
		this.Id = Id;
	}
	
	public int getId() {
		return Id;
	}

	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
	
	public String getImg() {
		return img;
	}
	
	public void setImg(String img) {
		this.img = img;
	}

}
