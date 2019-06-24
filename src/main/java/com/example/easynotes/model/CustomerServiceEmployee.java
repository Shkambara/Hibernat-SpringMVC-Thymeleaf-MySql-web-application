package com.example.easynotes.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity(name = "CustomerServiceEmployee")
public class CustomerServiceEmployee extends Employee{
	
	
	@Enumerated(EnumType.STRING)
	@Column(length = 8)
	@NotNull
	private TypeOfService typeOfService;
	
	public CustomerServiceEmployee() {
		
	}
	
	public CustomerServiceEmployee(String specialId,String name,String surname,TypeOfService typeofService,double salaryPerHour) throws Exception {
		super(specialId,name,surname,salaryPerHour);
		this.typeOfService = typeofService;
	}
	
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "employee", cascade = CascadeType.ALL)
	EmployeeHourRecords records;
	
	public EmployeeHourRecords getRecords() {
		
		return records;
	}
	
	public void setRecords(EmployeeHourRecords records) {
		
		this.records = records;
	}
	
	public TypeOfService getTypeOfService() {
		// TODO Auto-generated method stub
		return this.typeOfService;
	}

	public void setTypeOfService(TypeOfService typeOfService) {
		// TODO Auto-generated method stu
		this.typeOfService = typeOfService;
		
	}
	
}
