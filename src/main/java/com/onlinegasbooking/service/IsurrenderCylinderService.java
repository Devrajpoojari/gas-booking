package com.onlinegasbooking.service;

import com.onlinegasbooking.entity.SurrenderCylinder;

public interface IsurrenderCylinderService {

	public SurrenderCylinder insertSurrenderCylinder(SurrenderCylinder sc);
	
	public SurrenderCylinder updateSurrenderCylinder(SurrenderCylinder sc);
	
	public SurrenderCylinder deleteSurrenderCylinder(SurrenderCylinder sc);
	
	public int countSurrenderedCylinders();
}
