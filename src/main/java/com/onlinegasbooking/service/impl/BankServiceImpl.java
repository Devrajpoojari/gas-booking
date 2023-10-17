package com.onlinegasbooking.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlinegasbooking.entity.Bank;
import com.onlinegasbooking.exceptions.BankAlreadyExistsException;
import com.onlinegasbooking.exceptions.ResourceNotFoundException;
import com.onlinegasbooking.repository.IBankRepository;
import com.onlinegasbooking.service.IBankService;

/**
 * @author dhiragep this class is basically do all the operations related to
 *         bank transactions and other transactions
 *
 */
@Service
public class BankServiceImpl implements IBankService {

	@Autowired
	private IBankRepository bankRepository; // dependency injection or loose coupling

	/**
	 * insertBank method will insert all the bank information related to gas booking
	 * 
	 * @throws BankAlreadyExistsException
	 */

	@Override
	public Bank insertBank(Bank bank) throws BankAlreadyExistsException {

		Bank b1 = null;
		Optional<Bank> b = bankRepository.findBankByName(bank.getBankName());
		if (b.get().getBankName().equalsIgnoreCase(bank.getBankName())) {
			throw new BankAlreadyExistsException("Bank Already exists By Name :" + bank.getBankName());
		} else {
			b1 = bankRepository.save(bank);
		}

		return b1;
	}

	@Override
	public Bank updateBank(Bank bank) throws ResourceNotFoundException {
		Bank b = bankRepository.findById(bank.getBankId())
				.orElseThrow(() -> new ResourceNotFoundException("Bank deosn't exists by Id :" + bank.getBankId()));
		b.setBankName(bank.getBankName());
		b.setAddress(bank.getAddress());
		return bankRepository.save(b);
	}

	@Override
	public Bank deleteBank(Bank bank) throws ResourceNotFoundException {
		Bank b = bankRepository.findById(bank.getBankId())
				.orElseThrow(() -> new ResourceNotFoundException("Bank deosn't exists by Id :" + bank.getBankId()));
		bankRepository.delete(b);
		return b;
	}

}
