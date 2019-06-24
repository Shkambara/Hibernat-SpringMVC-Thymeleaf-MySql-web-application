package com.example.easynotes.model;

import java.beans.Transient;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class WorkingShift {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="shift_id")
	private Long id;

	
	@Column(name="shiftDate")
	private LocalDate date;
	
	@NotNull
	private double profitFromCash;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name="form_id", updatable = true, insertable = true)
	private EndOfTheDay form;
	
	@OneToMany(mappedBy="shift",cascade=CascadeType.ALL)
	private Set<SpecialSituation> situations = new HashSet<>();
	
	public WorkingShift(LocalDate date, double profitFromCash) {
		this.date = date;
		this.profitFromCash = profitFromCash;
	}
	
	public WorkingShift() {
		
	}
	
	public void setSpecialSituatuond(Set<SpecialSituation> situations) {
		this.situations = situations;
	}
	
	public Set<SpecialSituation> getSpecialSituations(){
		return situations;
	}
	
	public LocalDate getDate() {
		return date;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	public EndOfTheDay getEndOfTheDay() {
		return form;
	}
	
	public void setEndOfTheDay(EndOfTheDay eotd) {
		this.form = eotd;
	}
	
	@Transient
	public double countTotalProfit(double profitFromCash) {
		return profitFromCash + getProfitFromDrinks() + getProfitFromScoops();
	}
	
	public double getProfitFromCash() {
		return profitFromCash;
	}
	
	public void setProfitFromCash(double profitFromCash) {
		this.profitFromCash = profitFromCash;
	}

	
	@Transient
	public double getProfitFromDrinks() {
		return 0;
	}

	@Transient
	public double getProfitFromScoops() {
		return 0;
	}
	
	@Transient
	public double getProfit() {
		return profitFromCash + getProfitFromDrinks() + getProfitFromScoops();
	}
	
	
}
