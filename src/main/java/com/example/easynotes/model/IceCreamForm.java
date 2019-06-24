package com.example.easynotes.model;

import java.beans.Transient;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class IceCreamForm extends Form {
	
	@NotNull
	private Integer scoopsGoal;
	
	@OneToMany(mappedBy="iceForm",cascade=CascadeType.ALL)
	@OrderBy(value="date ASC")
	private Set<IceCreamDayData> dayData = new HashSet<>();
	
	public void setDayData(Set<IceCreamDayData> dayData) {
		this.dayData = dayData;
	}
	
	@JsonIgnore
	public Set<IceCreamDayData> getDayData() {
		return dayData;
	}
	
	
	public IceCreamForm() {
		super();
	}
	
	public IceCreamForm(LocalDate date,Integer scoopsGoal) {
		super(date);
		this.scoopsGoal = scoopsGoal;
	}
	
	
	public void setScoopsGoal(Integer scoopsGoal) {
		this.scoopsGoal = scoopsGoal;
	}
	
	
	
	public int getScoopsGoal() {
		if(scoopsGoal == null) {
			
			return 0;
		}else {
			
			return scoopsGoal;
		}
	}
	
	@Transient
	public double getTotalMonthProfit() {
		double sum = 0;
		
		for(IceCreamDayData day : dayData) {
			sum += day.getProfit();
		}
		
		return sum;
	}
	
	@Transient
	public int getTotalScoopsSold() {
		int sum = 0;
		
		for(IceCreamDayData day : dayData) {
			sum += day.getScoopsSold();
		}
		
		return sum;
	} 

}
