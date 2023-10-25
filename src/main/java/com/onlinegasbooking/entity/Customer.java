package com.onlinegasbooking.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "customer_id")
public class Customer extends AbstractUser {

//	private int cylinderId
	@OneToOne(cascade = CascadeType.MERGE,fetch = FetchType.LAZY)
	private Cylinder cylinder;

//	private int bankid;
	@OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	private Bank bank;
	
	private long accountNumber;
	
	private String ifscNo;
	
	@JsonBackReference
	@OneToMany(cascade = CascadeType.MERGE,fetch = FetchType.LAZY,mappedBy ="customer" )
	private List<GasBooking> bookings;

}
