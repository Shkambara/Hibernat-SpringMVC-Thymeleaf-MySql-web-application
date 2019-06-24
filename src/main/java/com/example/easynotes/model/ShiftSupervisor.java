package com.example.easynotes.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity
public class ShiftSupervisor extends Employee{
	
	@Id
	@Column(name="supervisor_id")
	private Long id;
	
	private static double minSalaryPerHour = 20.0;
	
	@NotNull
	private boolean atentedBHP;
	
	@OneToMany(mappedBy="supervisor",cascade=CascadeType.ALL)
	private Set<IceCreamForm> iceCreamForm;
	
	@OneToMany(mappedBy="supervisor",cascade=CascadeType.ALL)
	private Set<EndOfTheDay> profitForm;
	
	@OneToMany(mappedBy="supervisor",cascade=CascadeType.ALL)
	private Set<DrinkForm> drinkForm;
	
	@OneToMany(mappedBy = "supervisor")
	private Set<ShoppingOrder> shoppingOrders = new HashSet<ShoppingOrder>();
	
	@NotNull
	private double salaryPerHour;
	
	public ShiftSupervisor() {
		super();
	}	
	
	public ShiftSupervisor(String specialId, String name,String surname,boolean atentedBHP,double salaryPerHour) throws Exception {
		super(specialId,name,surname,salaryPerHour);
		this.atentedBHP = atentedBHP;
	}
	
	public boolean getAtentedBHP() {
		return atentedBHP;
	}
	
	public void setAtentedBHP(boolean atentedBHP) {
		this.atentedBHP = atentedBHP;
	}

	public Set<ShoppingOrder> getShoppingOrders() {
		return shoppingOrders;
	}

	public void setShoppingOrders(Set<ShoppingOrder> shoppingOrders) {
		this.shoppingOrders = shoppingOrders;
	}

	public double getSalaryPerHour() {
		return salaryPerHour;
	}

	public void setSalaryPerHour(double salaryPerHour) throws Exception {
		try {
			if(salaryPerHour > minSalaryPerHour) {
				this.salaryPerHour = salaryPerHour;
				
			}else {
				
				throw new Exception("Salary is too low for ShiftSupervisor");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
