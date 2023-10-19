package com.onlinegasbooking.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.onlinegasbooking.entity.Admin;
import com.onlinegasbooking.entity.Customer;
import com.onlinegasbooking.entity.GasBooking;
import com.onlinegasbooking.exceptions.AdminAlreadyExistsException;
import com.onlinegasbooking.exceptions.ResourceNotFoundException;
import com.onlinegasbooking.repository.IAdminRepository;
import com.onlinegasbooking.repository.ICustomerRepository;
import com.onlinegasbooking.service.IAdminService;

public class AdminServiceImpl implements IAdminService {

	@Autowired
	private IAdminRepository adminRepository;

	@Autowired
	private ICustomerRepository customerRepository;

	@Override
	public Admin insertAdmin(Admin admin) throws AdminAlreadyExistsException {
		Optional<?> a = adminRepository.getAdminByName(admin.getUserName());
		Admin ad = null;
		if (a.isPresent()) {
			throw new AdminAlreadyExistsException("Admin already exists with user name " + admin.getUserName());
		} else {
			ad = adminRepository.save(admin);
		}

		return ad;
	}

	@Override
	public Admin updateAdmin(Admin admin) throws ResourceNotFoundException {
		Admin ad = adminRepository.findById(admin.getUserId())
				.orElseThrow(() -> new ResourceNotFoundException("Admin Details are not found"));
		ad.setAddress(admin.getAddress());
		ad.setAdminName(admin.getAdminName());
		ad.setEmail(admin.getEmail());
		ad.setMobileNumber(admin.getMobileNumber());
		ad.setPassword(admin.getPassword());
		ad.setUserId(admin.getUserId());
		ad.setUserName(admin.getUserName());
		adminRepository.deleteById(ad.getUserId());
		return adminRepository.save(ad);
	}

	@Override
	public Admin deleteAdmin(long adminId) throws ResourceNotFoundException {
		Admin ad = adminRepository.findById(adminId)
				.orElseThrow(() -> new ResourceNotFoundException("Admin Details are not found"));
		adminRepository.delete(ad);
		return ad;
	}

	@Override
	public List<GasBooking> getAllBookings(long customerId) throws ResourceNotFoundException {
		Customer c= customerRepository.findById(customerId)
				.orElseThrow(() -> new ResourceNotFoundException("Customer not found with id : " + customerId));
		if(c.getBookings().isEmpty()) {
			throw new ResourceNotFoundException("No Bookings are availibale for this customer");
		}else {
			return c.getBookings();
		}
	}

//	@Override
//	public List<GasBooking> getAllBookingsForDays(long customerId, LocalDate fromDate, LocalDate toDate) throws ResourceNotFoundException {
//		Customer c= customerRepository.findById(customerId)
//				.orElseThrow(() -> new ResourceNotFoundException("Customer not found with id : " + customerId));
//		
//		List<GasBooking> listOfBookings=c.getBookings();
//		List<GasBooking> list=new ArrayList<>();
//		listOfBookings.stream().forEach(booking->{
//			if (fromDate.compareTo(toDate)) {
//				
//			}
//		});
//		
//		return null;
//	}

}
