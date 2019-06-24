package com.example.easynotes.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@MappedSuperclass
public abstract class Employee  {
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name = "emp_id")
	private Long id;
	
	@Column(name = "special_id",unique = true)
	@NotBlank
	private String specialId;
	@NotBlank
	private String name;
	@NotBlank
	private String surname;
	@NotNull
	private double salaryPerHour;
	
	public Employee() {
		
	}
	
	public Employee (String specialId, String name, String surname,double salaryPerHour) throws Exception{
		
		this.specialId = specialId;
		this.name = name;
		this.surname = surname + " IN THE EMPLOYEE";
		this.salaryPerHour = salaryPerHour;
	
	}
	
	public String toString() {
		return "Name="+this.name+"::Surname="+ this.surname;
	}
	
	public String getSpesialid() {
		return specialId;
	}
	
	public void setSpecialId(String specialId) {
		this.specialId = specialId;
	}
	
	
	public String getName() {
		return name;
	}
	
	public String getSurname() {
		return surname;
	}
	
	public double getSalaryPerHour() {
		return salaryPerHour;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public void setSalaryPerHour(double salaryPerHour) throws Exception {
		this.salaryPerHour = salaryPerHour;
	}
}
