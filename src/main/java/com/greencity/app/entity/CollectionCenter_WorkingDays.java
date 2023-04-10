package com.greencity.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "CollectionCenters_WorkingDays")
public class CollectionCenter_WorkingDays {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int workingDayId;
	@NotEmpty
	@Column
	private String workingDay; 
	
	/////////////////////////
	
	@ManyToOne
	@JoinColumn(name = "centerId")
	private CollectionCenter collectionCenter;
	
	//////////////////////////////////////////////////////
	
	public CollectionCenter getCollectionCenter() {
		return collectionCenter;
	}

	public void setCollectionCenter(CollectionCenter collectionCenter) {
		this.collectionCenter = collectionCenter;
	}

	public int getWorkingDayId() {
		return workingDayId;
	}

	public void setWorkingDayId(int workingDayId) {
		this.workingDayId = workingDayId;
	}

	public String getWorkingDay() {
		return workingDay;
	}

	public void setWorkingDay(String workingDay) {
		this.workingDay = workingDay;
	}
	
	



}
