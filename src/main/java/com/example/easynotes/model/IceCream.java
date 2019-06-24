package com.example.easynotes.model;

import java.beans.Transient;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class IceCream {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotNull
	private boolean hasSugar;
	
	@NotBlank
	private String name;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinTable(joinColumns= @JoinColumn(name="iceCream_id", referencedColumnName = "id"),
	inverseJoinColumns = @JoinColumn(name="day_id",referencedColumnName="day_id"))
	private Set<IceCreamDayData> iceData;
	
	@Enumerated(EnumType.STRING)
	@Column(length = 8)
	@NotNull
	private IceCreamType type;
	
	public IceCream(boolean hasSugar, String name, IceCreamType type) {
		this.hasSugar = hasSugar;
		this.name = name;
		this.type = type;
	}
	
	public IceCream() {
		
	}

	public void setIceCreamDayData(Set<IceCreamDayData> iceData) {
		this.iceData = iceData;
	}
	
	public Set<IceCreamDayData> getIceCreamDayData() {
		return iceData;
	}
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setHasSugar(boolean hasSugar) {
		this.hasSugar = hasSugar;
	}
	
	public boolean getHasSugar() {
		return hasSugar;
	}
	
	@Transient
	public String getHasSugarInfo() {
		
		if(getHasSugar()) {
			
			return "Has sugar: Yes";
		}else {
			
			return "Has sugar: No";
		}
	}
	
	@Transient
	public String getTypeInfo() {
		return "Type: " + getType();
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setType(IceCreamType type) {
		this.type = type;
	}
	
	public IceCreamType getType() {
		return type;
	}
	
	
	
	
}
