package com.onlinegasbooking.service.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	final static Logger logger=LoggerFactory.getLogger(BankServiceImpl.class);
	@Override
	public Bank insertBank(Bank bank) throws BankAlreadyExistsException {

		Bank b1 = null;
//		Optional<Bank> b = bankRepository.findBankByName(bank.getBankName());
//		if (b.get().getBankName().equalsIgnoreCase(bank.getBankName())) {
//			logger.error("In insert bank method");
//			throw new BankAlreadyExistsException("Bank Already exists By Name :" + bank.getBankName());
//		} else {
//			logger.info("Saving bank details in the database");
			b1 = bankRepository.save(bank);
//		}
//		logger.info("Returning bank details from the database");
		return b1;
	}

	@Override
	public Bank updateBank(Bank bank) throws ResourceNotFoundException {
		Bank b = bankRepository.findById(bank.getBankId())
				.orElseThrow(() -> new ResourceNotFoundException("Bank deosn't exists by Id :" + bank.getBankId()));
		b.setBankName(bank.getBankName());
		b.setAddress(bank.getAddress());
		bankRepository.deleteById(bank.getBankId());
		logger.info("Updating bank details from Database");
		return bankRepository.save(b);
	}

	@Override
	public Bank deleteBank(Bank bank) throws ResourceNotFoundException {
		Bank b = bankRepository.findById(bank.getBankId())
				.orElseThrow(() -> new ResourceNotFoundException("Bank deosn't exists by Id :" + bank.getBankId()));
		bankRepository.delete(b);
		logger.info("Deleting bank details from the database");
		return b;
	}

}
