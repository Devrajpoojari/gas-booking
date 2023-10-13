package com.onlinegasbooking.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class GasBooking {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long gasBookingId;
	
	@ManyToOne()    // bidrectional mapping 
	private Customer customer;
	
	private LocalDate bookingDate;
	
	private boolean status;
	
	private float bill;
	

}
