package com.example.easynotes.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;

import com.sun.istack.NotNull;

@Entity
public class EmployeeHourRecords {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="record_id")
	private Long id;
	
	@Enumerated
	@Column(length = 8)
	@NotNull
	private State state;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(
	          name = "emp_id"
	  )
	private CustomerServiceEmployee employee;
	
	@OneToMany(mappedBy="records")
	@OrderBy("shiftDate ASC")
	private Set<Shift> shifts = new HashSet<>();
	
	public EmployeeHourRecords(State state){
		this.state = state;
	}
	
	public EmployeeHourRecords() {
		
	}
	
	public Set<Shift> getShifts(){
		return shifts;
	}
	
	public void setShifts(Set<Shift> shifts) {
		this.shifts = shifts;
	}
	
	public void setEmployee(CustomerServiceEmployee employee) {
		this.employee = employee;
	}
	
	public CustomerServiceEmployee getEmployee() {
		return employee;
	}
	
	public void setState(State state) {
		this.state = state;
	}
	
	public State getState() {
		return state;
	}

	
	public int getSumHoursOfMonth() {
		int sum = 0;
		
		for(Shift shift : shifts) {
			sum+= shift.getSumOfWorkingHours();
		}
		
		return sum;
	}
	
}
