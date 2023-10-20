package com.onlinegasbooking.service;

import com.onlinegasbooking.entity.SurrenderCylinder;
import com.onlinegasbooking.exceptions.ResourceNotFoundException;

public interface IsurrenderCylinderService {

	public SurrenderCylinder insertSurrenderCylinder(SurrenderCylinder sc, long customerId , long cylinderId) throws ResourceNotFoundException;
	
	public SurrenderCylinder updateSurrenderCylinder(SurrenderCylinder sc) throws ResourceNotFoundException;
	
	public SurrenderCylinder deleteSurrenderCylinder(SurrenderCylinder sc) throws ResourceNotFoundException;
	
	public int countSurrenderedCylinders();
}
