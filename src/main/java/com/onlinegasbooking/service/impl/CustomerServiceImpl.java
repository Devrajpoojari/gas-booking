package com.onlinegasbooking.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlinegasbooking.entity.Customer;
import com.onlinegasbooking.exceptions.CustomerAlreadyExistsException;
import com.onlinegasbooking.exceptions.InvalidCredentials;
import com.onlinegasbooking.exceptions.ResourceNotFoundException;
import com.onlinegasbooking.repository.ICustomerRepository;
import com.onlinegasbooking.service.ICustomerService;

@Service
public class CustomerServiceImpl implements ICustomerService {

	@Autowired
	private ICustomerRepository customerRepository;

	@Override
	public Customer insertCustomer(Customer customer) throws CustomerAlreadyExistsException {
		Optional<Customer> c = customerRepository.getCustomerByUsername(customer.getUserName());

		if (c.isPresent()) {
			throw new CustomerAlreadyExistsException("Customer already present with name : " + customer.getUserName());
		} else {
			customer.setAccountNumber((long) Math.random());
			return customerRepository.save(customer);
		}

	}

	@Override
	public Customer updateCustomer(Customer customer) throws ResourceNotFoundException {
		Customer c = customerRepository.getCustomerByUsername(customer.getUserName())
				.orElseThrow(() -> new ResourceNotFoundException("Customer Not Found in the database"));
		c.setAddress(customer.getAddress());
		c.setEmail(customer.getEmail());
		c.setMobileNumber(customer.getMobileNumber());
		c.setUserName(customer.getUserName());
		c.setPassword(customer.getPassword());
		c.setIfscNo(customer.getIfscNo());

		return customerRepository.save(c);
	}

	@Override
	public Customer deleteCustomer(long customerId) throws ResourceNotFoundException {
		Customer c = customerRepository.findById(customerId)
				.orElseThrow(() -> new ResourceNotFoundException("Customer Not Found in the database"));
		customerRepository.delete(c);
		return c;
	}

	@Override
	public List<Customer> viewCustomer() throws ResourceNotFoundException {
		List<Customer> list = customerRepository.findAll();
		if(list.isEmpty()) {
			throw new ResourceNotFoundException("No customers are there in the database");
		}else {
			return list;
		}
	}

	@Override
	public Customer viewCustomer(long customerId) throws ResourceNotFoundException {
		
		return customerRepository.findById(customerId)
				.orElseThrow(() -> new ResourceNotFoundException("Customer Not Found in the database"));
	}

	@Override
	public Customer validateCustomer(String username, String password) throws ResourceNotFoundException, InvalidCredentials {
		Customer c = customerRepository.getCustomerByUsername(username)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid Credentials"));
		if(c.getUserName().equalsIgnoreCase(username) && c.getPassword().equalsIgnoreCase(password)) {
			return c;
		}
		else {
			throw new InvalidCredentials("Username or password is Invalid");
		}
	}

}
