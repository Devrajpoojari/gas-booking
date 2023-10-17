package com.onlinegasbooking.exceptions;

public class ResourceNotFoundException extends Exception {  //compile time exception 

	public ResourceNotFoundException(String msg) {
		super(msg);
	}
}
