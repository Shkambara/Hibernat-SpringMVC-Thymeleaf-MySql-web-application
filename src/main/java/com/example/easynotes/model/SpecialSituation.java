package com.example.easynotes.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class SpecialSituation{
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;
	
	@NotBlank
	private String title;
	
	private String description;
	
	@NotNull
	private int authorId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name="shift_id", nullable = false, updatable = true, insertable = true)
	private WorkingShift shift;
	
	private SpecialSituation(String title,String description, int authorId) {
		this.title = title;
		this.description = description;
		this.authorId = authorId;
		
	}
	
	public static SpecialSituation createSpecialSituations(String title,String description, int authorId) throws Exception {
		
		SpecialSituation situation = new SpecialSituation(title, description, authorId);
		
		return situation;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setWorkingShift(WorkingShift shift) {
		this.shift = shift;
	}
	
	public WorkingShift getWorkingShift() {
		return shift;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}
	
	public int getAuthorId() {
		return authorId;
	}
}
