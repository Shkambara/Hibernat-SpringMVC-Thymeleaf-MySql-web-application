package com.example.easynotes.model;

import java.beans.Transient;
import java.time.LocalDate;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

@Entity
public class DrinkDayData {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotNull
	@Column(name="drinkDate")
	private LocalDate date;
	
	@NotNull
	private int coctailsSold;
	@NotNull 
	private int juiceSold;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name="form_id",  nullable = false, updatable = true, insertable = true)
	private DrinkForm drinkForm;
	
	private static double priceForJuice = 13.5;
	private static double priceForCoctail = 14.5;
	
	public DrinkDayData() {
		
	}
	
	public DrinkDayData(LocalDate date, int coctailsSold,int juiceSold) {
		this.date = date;
		this.coctailsSold = coctailsSold;
		this.juiceSold = juiceSold;
	}
	
	public Long getDrinkForm_id() {	
		return drinkForm.getId(); 
	}
	
	@JsonIgnore
	@Basic
	public DrinkForm getDrinkForm() {
		return drinkForm;
	}
	
	public void setDrinkForm(DrinkForm drinkForm) {
		this.drinkForm = drinkForm;
	}
	
	
	public void setId(Long id) {
		this.id = id;
	}
	
	@Basic
	public Long getId() {
		return id;
	}
	
	@Basic
	public LocalDate getDate() {
		return date;
	}
	
	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	public void setCoctailsSold(int coctailsSold) {
		this.coctailsSold = coctailsSold;
	}
	@Basic
	public int getCoctailsSold() {
		return coctailsSold;
	}
	
	public void setJuiceSold(int juiceSold) {
		this.juiceSold = juiceSold;
	}
	@Basic
	public int getJuiceSold() {
		return juiceSold;
	}
	@Transient
	public double getProfit() {
		return (coctailsSold * priceForCoctail) + (juiceSold * priceForJuice);
	}
	
}
