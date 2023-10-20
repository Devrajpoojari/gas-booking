package com.onlinegasbooking.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onlinegasbooking.entity.Admin;
import com.onlinegasbooking.entity.GasBooking;
import com.onlinegasbooking.exceptions.ResourceNotFoundException;
import com.onlinegasbooking.service.IAdminService;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

	@Autowired
	private IAdminService adminService;

	@PostMapping("/add-admin")
	public ResponseEntity<Admin> insertAdmin(@RequestBody Admin admin) throws Exception {
		if (admin == null) {
			throw new Exception("Object is null");
		} else {
			return new ResponseEntity<Admin>(adminService.insertAdmin(admin), HttpStatus.CREATED);
		}

	}

	@PutMapping("/update-admin")
	public ResponseEntity<Admin> updateAdmin(@RequestBody Admin admin) throws Exception {
		if (admin == null) {
			throw new Exception("Object is null");
		} else {
			return new ResponseEntity<Admin>(adminService.updateAdmin(admin), HttpStatus.OK);
		}
	}

	@DeleteMapping("/delete/{adminId}")
	public ResponseEntity<Admin> deleteAdmin(@PathVariable(name = "adminId") long adminId) throws Exception {
		if (adminId == 0) {
			throw new Exception("Admin Id Shouldn't be Zero..");
		} else {
			return new ResponseEntity<Admin>(adminService.deleteAdmin(adminId), HttpStatus.OK);
		}
	}

	@GetMapping("/get-all-bookings-by-cutomerid/{customerId}")
	public ResponseEntity<List<GasBooking>> getAllBookings(long customerId) throws ResourceNotFoundException {
		return new ResponseEntity<List<GasBooking>>(adminService.getAllBookings(customerId), HttpStatus.OK);
	}

	@GetMapping("/get-all-bookings-for-days/{customerId}/{fromDate}/{toDate}")
	public ResponseEntity<List<GasBooking>> getAllBookingsForDays(@PathVariable long customerId,
			@PathVariable LocalDate fromDate, @PathVariable LocalDate toDate) throws Exception {
		if (fromDate == null && toDate == null && customerId == 0) {
			throw new Exception("Some thing went wrong in inputs");
		} else {
			return new ResponseEntity<List<GasBooking>>(
					adminService.getAllBookingsForDays(customerId, fromDate, toDate), HttpStatus.OK);

		}

	}

}
