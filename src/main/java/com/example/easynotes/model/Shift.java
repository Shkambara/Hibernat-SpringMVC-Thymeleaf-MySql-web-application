package com.example.easynotes.model;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Shift {
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;
	
	@NotNull
	private LocalTime begining;
	
	@NotNull
	private LocalTime ending;
	
	@NotNull
	private int sumOfWorkingHours;
	
	@NotNull
	@Column(name="shiftDate")
	private LocalDate date;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name="record_id",  nullable = false, updatable = true, insertable = true)
	@JsonIgnore
	private EmployeeHourRecords records;
	
	public Shift(LocalTime begining,LocalTime ending,LocalDate date) {
		this.begining = begining;
		this.ending = ending;
		this.date = date;
		this.sumOfWorkingHours = ending.getHour() - begining.getHour();
	}
	
	public Shift() {
		
	}
	
	public void setRecords(EmployeeHourRecords records) {
		this.records = records;
	}
	
	public EmployeeHourRecords getRecords() {
		return records;
	}
	
	public void setBegining(LocalTime begining) {
		this.begining = begining;
	}
	
	public LocalTime getBegining() {
		return begining;
	}
	
	public void setEnding(LocalTime ending) {
		this.ending = ending;
	}
	
	public LocalTime getEnding() {
		return ending;
	}
	
	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	public LocalDate getDate() {
		return date;
	}
	
	public int getSumOfWorkingHours() {
		return sumOfWorkingHours;
	}
	
	public void setSumOfWorkingHours(int sumOfWorkingHours) {
		this.sumOfWorkingHours = sumOfWorkingHours;
	}
	
	
}
