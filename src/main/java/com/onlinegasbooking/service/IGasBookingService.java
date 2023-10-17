package com.onlinegasbooking.service;

import com.onlinegasbooking.entity.GasBooking;

public interface IGasBookingService {
	
	public GasBooking insertGasBooking(GasBooking gasBooking);
	
	public GasBooking updateGasBooking(GasBooking gasBooking);
	
	public GasBooking deleteGasBooking(long bookingId);
	
	public GasBooking getBill(long customerId);
}
