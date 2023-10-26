package com.onlinegasbooking.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.onlinegasbooking.entity.Bank;
import com.onlinegasbooking.exceptions.BankAlreadyExistsException;
import com.onlinegasbooking.exceptions.ResourceNotFoundException;

@SpringBootTest // spin the project when we run the junit
class IBankServiceTest {

	@Autowired
	private IBankService bankService;

	@Test
	void testInsertBank() throws BankAlreadyExistsException {
		Bank b = Bank.builder().address("Hydrabad").bankName("SBH").bankId(12L).build();
		Bank ba = bankService.insertBank(b);
		System.out.println(ba);
		
		assertEquals(b.getBankName(), ba.getBankName());
		
	}

	@Test
	void testUpdateBank() throws BankAlreadyExistsException, ResourceNotFoundException {
		Bank b = Bank.builder().address("Hydrabad").bankName("SBI").bankId(13L).build();
		Bank ba = bankService.insertBank(b);
		
		ba.setAddress("Banglore");
		
		Bank up = bankService.updateBank(ba);
		assertEquals(up.getAddress(), ba.getAddress());
	}

	@Test
	void testDeleteBank() throws BankAlreadyExistsException, ResourceNotFoundException {
		Bank b = Bank.builder().address("Hydrabad").bankName("ICICI").bankId(13L).build();
		Bank ba = bankService.insertBank(b);
		
		Bank dBank=bankService.deleteBank(ba);
		
		assertEquals(b.getAddress(), dBank.getAddress());
		
		
	}

}
