package com.onlinegasbooking.service;

import java.util.List;

import com.onlinegasbooking.entity.Cylinder;
import com.onlinegasbooking.exceptions.ResourceNotFoundException;

public interface ICylinderService {

	public Cylinder insertCylinder(Cylinder cylinder);

	public Cylinder updateCylinder(Cylinder cylinder) throws ResourceNotFoundException;

	public Cylinder deleteCylinder(long cylinderId) throws ResourceNotFoundException;

	public List<Cylinder> viewCylinderByType(String type) throws ResourceNotFoundException;

}
