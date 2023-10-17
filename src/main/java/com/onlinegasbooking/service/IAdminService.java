package com.onlinegasbooking.service;

import java.time.LocalDate;
import java.util.List;

import com.onlinegasbooking.entity.Admin;
import com.onlinegasbooking.entity.GasBooking;

public interface IAdminService {

	public Admin insertAdmin(Admin admin);

	public Admin updateAdmin(Admin admin);

	public Admin deleteAdmin(long adminId);

	public List<GasBooking> getAllBookings(long customerId);

	public List<GasBooking> getAllBookingsForDays(long customerId, LocalDate formDate, LocalDate toDate);

}
