package com.onlinegasbooking;

import org.slf4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class GasBookingApplication {

	final static Logger logger = org.slf4j.LoggerFactory.getLogger(GasBookingApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(GasBookingApplication.class, args);
		logger.info("Application is Running...........");

	}
	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.onlinegasbooking")).build();
	}

	// http://localhost:1430/swagger-ui.html#/

}
