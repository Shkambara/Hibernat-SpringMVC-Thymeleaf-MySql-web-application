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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.sun.istack.NotNull;

@Entity
public class IceCreamDayData {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="day_id")
	private Long id;
	
	@Column(name="iceDate")
	private LocalDate date;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name="form_id",updatable = true, insertable = true)
	private IceCreamForm iceForm;
	
	@ManyToMany(mappedBy="iceData",cascade=CascadeType.ALL)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Set<IceCream> iceCreamMade = new HashSet<>();
	
	
	private static int scoopsPerCuvvet = 60;
	private static double priceForScoop = 3.5;
	
	@NotNull
	private int scoopsSold;
	
	public IceCreamDayData() {
		
	}
	
	
	public void setIceCreamMade(Set<IceCream> iceCreamMade) {
		this.iceCreamMade = iceCreamMade;
	}
	
	public Set<IceCream> getIceCreamMade(){
		return iceCreamMade;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setIceForm(IceCreamForm iceForm) {
		this.iceForm = iceForm;
	}
	
	public IceCreamForm getIceForm() {
		return iceForm;
	}

	
	public IceCreamDayData(int scoopsSold) {
		this.scoopsSold = scoopsSold;
		
	}
	
	public LocalDate getDate() {
		return date;
	}
	
	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	public int getScoopsSold() {
		return scoopsSold;
	}
	
	public void setScoopsSold( int scoopsSold) {
		this.scoopsSold = scoopsSold;
	}
	
	@Transient
	public double getProfit() {
		return scoopsSold * priceForScoop;
	}
	
	@Transient
	public int getCuvvets() {
		return scoopsSold/scoopsPerCuvvet;
	}
	
	
}
