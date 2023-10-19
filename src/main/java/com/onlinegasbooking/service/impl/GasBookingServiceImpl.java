package com.onlinegasbooking.service.impl;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlinegasbooking.entity.Customer;
import com.onlinegasbooking.entity.GasBooking;
import com.onlinegasbooking.exceptions.ResourceNotFoundException;
import com.onlinegasbooking.repository.ICustomerRepository;
import com.onlinegasbooking.repository.IGasBookingRepository;
import com.onlinegasbooking.service.IGasBookingService;

@Service
public class GasBookingServiceImpl implements IGasBookingService {

	private IGasBookingRepository bookingRepository; // feild injection

	private ICustomerRepository customerRepository;

	@Autowired
	public GasBookingServiceImpl(IGasBookingRepository bookingRepository, ICustomerRepository customerRepository) { // constructor
																													// based
																													// injection
		this.bookingRepository = bookingRepository;
		this.customerRepository = customerRepository;
	}

	@Override
	public GasBooking insertGasBooking(GasBooking gasBooking, long customerId) throws ResourceNotFoundException {
		Customer c = customerRepository.findById(customerId)
				.orElseThrow(() -> new ResourceNotFoundException("Customer Doesn;t exists with id : " + customerId));
		gasBooking.setBookingDate(LocalDate.now());
		gasBooking.setCustomer(c);

		return bookingRepository.save(gasBooking);
	}

	@Override
	public GasBooking updateGasBooking(GasBooking gasBooking) throws ResourceNotFoundException {
		GasBooking gas = bookingRepository.findById(gasBooking.getGasBookingId()).orElseThrow(() -> new ResourceNotFoundException(
				"Gas Booking details not exists with booking id :" + gasBooking.getGasBookingId()));
		gas.setBill(gasBooking.getGasBookingId());
		gas.setStatus(gasBooking.isStatus());
		gas.setBill(gasBooking.getBill());
		
		return bookingRepository.save(gas);
	}

	@Override
	public GasBooking deleteGasBooking(long bookingId) throws ResourceNotFoundException {
		GasBooking gas = bookingRepository.findById(bookingId).orElseThrow(() -> new ResourceNotFoundException(
				"Gas Booking details not exists with booking id :" + bookingId));
		bookingRepository.delete(gas);
		return gas;
	}

	@Override
	public float getBill(long customerId) throws ResourceNotFoundException {
		Customer c = customerRepository.findById(customerId)
				.orElseThrow(() -> new ResourceNotFoundException("Customer Doesn;t exists with id : " + customerId));
		float bill=0;
		for(GasBooking gas:c.getBookings()) {
			bill=bill+gas.getBill();
		}
		
		return bill;
	}

}
