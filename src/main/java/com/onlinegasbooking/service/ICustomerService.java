package com.onlinegasbooking.service;

import java.util.List;

import com.onlinegasbooking.entity.Customer;

public interface ICustomerService {

	public Customer insertCustomer(Customer customer);

	public Customer updateCustomer(Customer customer);

	public Customer deleteCustomer(long customerId);

	public List<Customer> viewCustomer();

	public Customer viewCustomer(long customerId);

	public Customer validateCustomer(String username, String password);

}
