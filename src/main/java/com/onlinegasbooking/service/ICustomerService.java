package com.onlinegasbooking.service;

import java.util.List;

import com.onlinegasbooking.entity.Customer;
import com.onlinegasbooking.exceptions.CustomerAlreadyExistsException;
import com.onlinegasbooking.exceptions.InvalidCredentials;
import com.onlinegasbooking.exceptions.ResourceNotFoundException;

public interface ICustomerService {

	public Customer insertCustomer(Customer customer) throws CustomerAlreadyExistsException;

	public Customer updateCustomer(Customer customer) throws ResourceNotFoundException;

	public Customer deleteCustomer(long customerId) throws ResourceNotFoundException;

	public List<Customer> viewCustomer() throws ResourceNotFoundException;

	public Customer viewCustomer(long customerId) throws ResourceNotFoundException;

	public Customer validateCustomer(String username, String password)
			throws ResourceNotFoundException, InvalidCredentials;

}
