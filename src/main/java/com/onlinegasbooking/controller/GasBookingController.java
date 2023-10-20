package com.onlinegasbooking.controller;

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

import com.onlinegasbooking.entity.GasBooking;
import com.onlinegasbooking.service.IGasBookingService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/gas-booking")
public class GasBookingController {

	@Autowired
	private IGasBookingService bookingService;

	@ApiOperation(value = "Inserting Gas Booking Objects ")
	@PostMapping("/insert-gas-booking/{customerId}")
	public ResponseEntity<GasBooking> insertGasBooking(@RequestBody GasBooking gasBooking,
			@PathVariable long customerId) throws Exception {
		if (gasBooking == null && customerId == 0) {
			throw new Exception("the object is Null either Empty, zero");
		} else {
			return new ResponseEntity<>(bookingService.insertGasBooking(gasBooking, customerId), HttpStatus.CREATED);
		}
	}

	@PutMapping("/update-gas-booking")
	public ResponseEntity<GasBooking> updateGasBooking(@RequestBody GasBooking gasBooking) throws Exception {
		if (gasBooking == null) {
			throw new Exception("the object is Null either Empty, zero");
		} else {
			return new ResponseEntity<>(bookingService.updateGasBooking(gasBooking), HttpStatus.OK);
		}
	}

	@DeleteMapping("/delete/{bookingId}")
	public ResponseEntity<?> deleteGasBooking(@PathVariable long bookingId) throws Exception {
		if (bookingId == 0) {
			throw new Exception("the object is Null either Empty, zero");
		} else {
			return new ResponseEntity<>(bookingService.deleteGasBooking(bookingId), HttpStatus.OK);
		}
	}

	@GetMapping("/get-bill/{customerId}")
	public ResponseEntity<?> getBill(@PathVariable long customerId) throws Exception {
		if (customerId == 0) {
			throw new Exception("the object is Null either Empty, zero");
		} else {
			return new ResponseEntity<>(bookingService.getBill(customerId), HttpStatus.OK);
		}
	}

}
