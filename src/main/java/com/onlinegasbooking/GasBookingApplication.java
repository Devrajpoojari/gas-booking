package com.onlinegasbooking;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GasBookingApplication {

	final static Logger logger = org.slf4j.LoggerFactory.getLogger(GasBookingApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(GasBookingApplication.class, args);
		logger.info("Application is Running...........");

	}

}
