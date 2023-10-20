package com.onlinegasbooking.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlinegasbooking.entity.Cylinder;
import com.onlinegasbooking.exceptions.ResourceNotFoundException;
import com.onlinegasbooking.repository.ICylinderRepository;
import com.onlinegasbooking.service.ICylinderService;

@Service
public class CylinderServiceImpl implements ICylinderService {

	@Autowired
	private ICylinderRepository cylinderRepository;

	@Override
	public Cylinder insertCylinder(Cylinder cylinder) {

		return cylinderRepository.save(cylinder);
	}

	@Override
	public Cylinder updateCylinder(Cylinder cylinder) throws ResourceNotFoundException {
		Cylinder c = cylinderRepository.findById(cylinder.getCyclinderId()).orElseThrow(
				() -> new ResourceNotFoundException("Cylinder Not Found with Id : " + cylinder.getCyclinderId()));
		c.setPrice(cylinder.getPrice());
		c.setStrapColor(cylinder.getStrapColor());
		c.setType(cylinder.getType());
		c.setWeight(cylinder.getWeight());

		return cylinderRepository.save(c);
	}

	@Override
	public Cylinder deleteCylinder(long cylinderId) throws ResourceNotFoundException {
		Cylinder c = cylinderRepository.findById(cylinderId)
				.orElseThrow(() -> new ResourceNotFoundException("Cylinder Not Found with Id : " + cylinderId));
		cylinderRepository.delete(c);
		return c;
	}

	@Override
	public List<Cylinder> viewCylinderByType(String type) throws ResourceNotFoundException {
		List<Cylinder> listOfCylinders = cylinderRepository.findAll().stream()
				.filter(t -> t.getType().equalsIgnoreCase(type)).collect(Collectors.toList());
		if (listOfCylinders.isEmpty()) {
			throw new ResourceNotFoundException("No Cylinders Found for this Type : " + type);
		} else {
			return listOfCylinders;
		}

	}

}
