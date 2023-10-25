package com.onlinegasbooking.controller;

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

import com.onlinegasbooking.entity.Customer;
import com.onlinegasbooking.exceptions.CustomerAlreadyExistsException;
import com.onlinegasbooking.exceptions.InvalidCredentials;
import com.onlinegasbooking.exceptions.ResourceNotFoundException;
import com.onlinegasbooking.service.ICustomerService;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

	@Autowired
	private ICustomerService customerService;

	@PostMapping("/insert-customer")
	public ResponseEntity<Customer> insertCustomer(@RequestBody Customer customer) throws Exception {
		if (customer != null) {
			return new ResponseEntity<Customer>(customerService.insertCustomer(customer), HttpStatus.CREATED);
		}
		throw new Exception("Customer Object is null or empty");
	}

	@PutMapping("/update-customer")
	public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer) throws Exception {
		if (customer != null) {
			return new ResponseEntity<Customer>(customerService.updateCustomer(customer), HttpStatus.OK);
		}
		throw new Exception("Customer Object is null or empty");
	}

	@DeleteMapping("/delete-customer/{customerId}")
	public ResponseEntity<Customer> deleteCustomer(@PathVariable long customerId) throws Exception {
		if (customerId == 0) {
			throw new Exception("Customer Id is Invalid");
		}
		return new ResponseEntity<Customer>(customerService.deleteCustomer(customerId), HttpStatus.OK);
	}

	@GetMapping("/veiw-all-customers")
	public ResponseEntity<List<Customer>> viewCustomer() throws ResourceNotFoundException {
		return new ResponseEntity<List<Customer>>(customerService.viewCustomer(), HttpStatus.OK);
	}

	@GetMapping("/view-customer-by-id/{customerId}")
	public ResponseEntity<Customer> viewCustomer(@PathVariable long customerId) throws Exception {
		if (customerId == 0) {
			throw new Exception("Customer Id is Invalid");
		}
		return new ResponseEntity<Customer>(customerService.viewCustomer(customerId), HttpStatus.OK);
	}

	@GetMapping("/authenticate-customer/{username}/{password}")
	public ResponseEntity<Customer> validateCustomer(@PathVariable String username, @PathVariable String password)
			throws ResourceNotFoundException, InvalidCredentials {
		if (username == null && username == "" && password == null && password == "") {
			throw new InvalidCredentials("Username and password is incorrect");
		} else {
			return new ResponseEntity<Customer>(customerService.validateCustomer(username, password),
					HttpStatus.ACCEPTED);
		}
	}

}
