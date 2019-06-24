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
public class EndOfTheDay extends Form {
	
	@OneToMany(mappedBy="form",cascade=CascadeType.ALL)
	@OrderBy(value="date ASC")
	private Set<WorkingShift> shifts = new HashSet<>();
	
	
	public EndOfTheDay(LocalDate date) {
		super(date);
	}
	
	
	public EndOfTheDay() {
		super();
	}
	
	public void setShifts(Set<WorkingShift> shifts) {
		this.shifts = shifts;
	}
	
	@JsonIgnore
	public Set<WorkingShift> getShifts(){
		return shifts;
	}
	
	@Transient
	public double getTotalMonthProfit() {
		double sum = 0;
		
		for(WorkingShift shift : shifts) {
			sum+= shift.getProfit();
		}
		
		return sum;
	}
	
	
}
