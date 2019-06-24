package com.example.easynotes.model;

import java.beans.Transient;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@MappedSuperclass
public abstract class Form{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="form_id")
	private Long id;
	
	@NotNull
	private LocalDate date;
	
	@Enumerated(EnumType.STRING)
	@Column(length = 8)
	@NotNull
	private State state;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name="supervisor_id", updatable = true, insertable = true)
	private ShiftSupervisor supervisor;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name="emp_id", updatable = true, insertable = true)
	private Administrator admin;
	
	public Form() {
		
	}
	
	public Form(LocalDate date) {
		this.setDate(date);
		this.setState(State.NOTSENT);
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public State getState() {
		return this.state;
	}

	public void setState(State state) {
		this.state = state;
	}

	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}
	
	@Transient
	public String getMonthAndYear() {
		return getDate().getMonth() + " " + getDate().getYear();
	}

	public Administrator getAdmin() {
		return admin;
	}

	public void setAdmin(Administrator admin) {
		this.admin = admin;
	}

	public ShiftSupervisor getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(ShiftSupervisor supervisor) {
		this.supervisor = supervisor;
	}
	
	
}
