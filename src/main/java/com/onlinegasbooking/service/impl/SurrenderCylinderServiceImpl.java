package com.onlinegasbooking.service.impl;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlinegasbooking.entity.Customer;
import com.onlinegasbooking.entity.Cylinder;
import com.onlinegasbooking.entity.SurrenderCylinder;
import com.onlinegasbooking.exceptions.ResourceNotFoundException;
import com.onlinegasbooking.repository.ICustomerRepository;
import com.onlinegasbooking.repository.ICylinderRepository;
import com.onlinegasbooking.repository.ISurrenderCylinderRepository;
import com.onlinegasbooking.service.IsurrenderCylinderService;

@Service
public class SurrenderCylinderServiceImpl implements IsurrenderCylinderService {

	@Autowired
	private ISurrenderCylinderRepository surrenderCylinderRepository;

	@Autowired
	private ICustomerRepository customerRepository;

	@Autowired
	private ICylinderRepository cylinderRepository;

	@Override
	public SurrenderCylinder insertSurrenderCylinder(SurrenderCylinder sc, long customerId, long cylinderId)
			throws ResourceNotFoundException {
		Customer c = customerRepository.findById(customerId)
				.orElseThrow(() -> new ResourceNotFoundException("Customer Doesn't exists with id : " + customerId));

		Cylinder cylinder = cylinderRepository.findById(cylinderId)
				.orElseThrow(() -> new ResourceNotFoundException("Cylinder Not Exists with Id : " + cylinderId));
		sc.setCustomer(c);
		sc.setCylinder(cylinder);
		sc.setSurrenderDate(LocalDate.now());

		return surrenderCylinderRepository.save(sc);
	}

	@Override
	public SurrenderCylinder updateSurrenderCylinder(SurrenderCylinder sc) throws ResourceNotFoundException {
		SurrenderCylinder s = surrenderCylinderRepository.findById(sc.getSurrenderId()).orElseThrow(
				() -> new ResourceNotFoundException("Surrendered cylinder Not Exists with id :" + sc.getSurrenderId()));
		s.setSurrenderDate(LocalDate.now());
		return surrenderCylinderRepository.save(s);
	}

	@Override
	public SurrenderCylinder deleteSurrenderCylinder(SurrenderCylinder sc) throws ResourceNotFoundException {
		SurrenderCylinder s = surrenderCylinderRepository.findById(sc.getSurrenderId()).orElseThrow(
				() -> new ResourceNotFoundException("Surrendered cylinder Not Exists with id :" + sc.getSurrenderId()));
		surrenderCylinderRepository.delete(s);

		return s;
	}

	@Override
	public int countSurrenderedCylinders() {

		return surrenderCylinderRepository.findAll().size();
	}

}
