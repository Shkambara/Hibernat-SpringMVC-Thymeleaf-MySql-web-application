package com.example.easynotes.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@Entity(name = "ShoppingOrder")
public class ShoppingOrder{
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name="shop_id")
	private Long id;
	
	@JsonDeserialize(using = YourDateDeserializer.class)
	private Date dateOfPlannedDelivery;
	
	@NotNull
	private double sum;
	
	@ManyToOne(
	          fetch = FetchType.LAZY,
	          optional = false
	  )
	  @JoinColumn(
	          name = "emp_id"
	  )
	@JsonIgnore
    private ShiftSupervisor supervisor;
	
	@OneToMany(mappedBy="shoppingOrder")
	private Set<Product> products;

//THIS IS PROTOTYPE**
	public void addProductToCart(Product product) throws Exception {
		/*if(product.getIsAvailable()) {
			products.add(product);
			//this.addAssociation(Role.PRODUCTS, Role.ORDERS, product);
		}else {
			throw new Exception("It seems that the product is not available");
		}*/
	}
	
	public void removeProductFromCart(Product product) throws Exception {
		/*for(int i = 0; i<products.size();i++) {
			if(products.get(i) == product) {
				products.remove(i);
				return;
			}
		}*/
		throw new Exception("Product seems to be alredy removed");
	}
	
	public void setDateOfPlannedDelivery( Date dateOfPlannedDelivery) {
		this.dateOfPlannedDelivery = dateOfPlannedDelivery;
	}
	
	public Date getDateOfPlannedDelivery() {
		return dateOfPlannedDelivery;
	}
	
	public void setSum(double sum) {
		this.sum = sum;
	}
	
	public double getSum() {
		return sum;
	}
	
	
}

