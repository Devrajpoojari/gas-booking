package com.onlinegasbooking.entity;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@Data
@NoArgsConstructor
public class SurrenderCylinder {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long surrenderId;
	
	private LocalDate surrenderDate;
	
	@OneToOne
	private Customer customer;
	
	@OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private Cylinder cylinder;

}
