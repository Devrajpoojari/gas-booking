package com.onlinegasbooking.service;

import java.util.List;

import com.onlinegasbooking.entity.Cylinder;

public interface ICylinderService {

	public Cylinder insertCylinder(Cylinder cylinder);

	public Cylinder updateCylinder(Cylinder cylinder);

	public Cylinder deleteCylinder(long cylinderId);

	public List<Cylinder> viewCylinderByType(String type);

}
