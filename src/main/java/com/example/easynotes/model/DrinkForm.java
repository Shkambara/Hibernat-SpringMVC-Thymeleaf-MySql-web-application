package com.example.easynotes.model;

import java.beans.Transient;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class DrinkForm extends Form {
	
	private static int drinkPerLitr = 400;
	
	@OneToMany(mappedBy="drinkForm",cascade=CascadeType.ALL)
	@OrderBy(value="date ASC")
	private Set<DrinkDayData> dayData = new HashSet<>();
	
	public void setDayData(Set<DrinkDayData> dayData) {
		this.dayData = dayData;
	}
	
	@JsonIgnore
	public Set<DrinkDayData> getDayData(){
		return dayData;
	}
	
	
	public DrinkForm(LocalDate date) {
		super(date);
		
	}
	
	public DrinkForm() {
		super();
	}
	
	@Transient
	public double getTotalMonthProfit() {
		double sum = 0;
		
		for(DrinkDayData day : dayData) {
			sum += day.getProfit();
		}
		
		return sum;
	}
	
	@Transient
	public String getDrinkPerLitr() {
		return "drink per litrs: " + drinkPerLitr +"ml";
	}
	
	@Transient
	public int getTotalDrinksSold() {
		int sum = 0;
		
		for(DrinkDayData day : dayData) {
			System.out.println(sum);
			sum += (day.getCoctailsSold() + day.getJuiceSold());
		}
		
		return sum;
	}
	
	
	
	
	

}
