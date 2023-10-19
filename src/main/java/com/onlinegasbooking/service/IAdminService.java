package com.onlinegasbooking.service;

import java.time.LocalDate;
import java.util.List;

import com.onlinegasbooking.entity.Admin;
import com.onlinegasbooking.entity.GasBooking;
import com.onlinegasbooking.exceptions.AdminAlreadyExistsException;
import com.onlinegasbooking.exceptions.ResourceNotFoundException;

public interface IAdminService {

	public Admin insertAdmin(Admin admin) throws AdminAlreadyExistsException;

	public Admin updateAdmin(Admin admin) throws ResourceNotFoundException;

	public Admin deleteAdmin(long adminId) throws ResourceNotFoundException;

	public List<GasBooking> getAllBookings(long customerId) throws ResourceNotFoundException;

//	public List<GasBooking> getAllBookingsForDays(long customerId, LocalDate fromDate, LocalDate toDate);

}
