package com.onlinegasbooking.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cylinder {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long cyclinderId;
	
	private String type;
	
	private float weight;
	
	private String strapColor;
	
	private float price;

}
