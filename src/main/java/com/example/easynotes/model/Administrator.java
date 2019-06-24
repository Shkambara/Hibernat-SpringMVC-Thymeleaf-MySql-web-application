package com.example.easynotes.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity
public class Administrator extends Employee {
	
	@NotNull
	private boolean specialRights;
	
	@OneToMany(mappedBy="admin",cascade=CascadeType.ALL)
	private Set<IceCreamForm> iceCreamForm;
	
	@OneToMany(mappedBy="admin",cascade=CascadeType.ALL)
	private Set<EndOfTheDay> profitForm;
	
	@OneToMany(mappedBy="admin",cascade=CascadeType.ALL)
	private Set<DrinkForm> drinkForm;
	
	public Administrator() {
		
	}
	
	public Administrator(String specialId,String name, String surname,double salaryPerHour,boolean specialRights) throws Exception {
		super(specialId, name, surname, salaryPerHour);
		this.specialRights = specialRights;
	}
	
	public void setSpecialRights(boolean specialRights) {
		this.specialRights =specialRights;
	}
	
	public boolean getSpecialRights() {
		return specialRights;
	}

	public Set<IceCreamForm> getIceCreamForm() {
		return iceCreamForm;
	}

	public void setIceCreamForm(Set<IceCreamForm> iceCreamForm) {
		this.iceCreamForm = iceCreamForm;
	}

	public Set<EndOfTheDay> getProfitForm() {
		return profitForm;
	}

	public void setProfitForm(Set<EndOfTheDay> profitForm) {
		this.profitForm = profitForm;
	}

	public Set<DrinkForm> getDrinkForm() {
		return drinkForm;
	}

	public void setDrinkForm(Set<DrinkForm> drinkForm) {
		this.drinkForm = drinkForm;
	}

	
	
	
}
