package com.onlinegasbooking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onlinegasbooking.entity.Cylinder;
import com.onlinegasbooking.exceptions.ResourceNotFoundException;
import com.onlinegasbooking.service.ICylinderService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("api/cylinder")
public class CylinderController {

	@Autowired
	private ICylinderService cylinderService;

	@PostMapping("/insert-cylinder")
	public ResponseEntity<Cylinder> insertCylinder(@RequestBody Cylinder cylinder) throws Exception {
		if (cylinder == null) {
			throw new Exception("Cylinder Object is null");
		}
		return new ResponseEntity<Cylinder>(cylinderService.insertCylinder(cylinder), HttpStatus.CREATED);
	}

	@PutMapping("/update-cylinder")
	public ResponseEntity<Cylinder> updateCylinder(@RequestBody Cylinder cylinder) throws Exception {
		if (cylinder == null) {
			throw new Exception("Cylinder Object is null");
		}
		return new ResponseEntity<Cylinder>(cylinderService.updateCylinder(cylinder), HttpStatus.OK);
	}

	@ApiOperation(value = "Deleting cylinder")
	@DeleteMapping("/delete-cylinder/{cylinderId}")
	public ResponseEntity<Cylinder> deleteCylinder(@PathVariable long cylinderId) throws Exception {
		if (cylinderId == 0) {
			throw new Exception("Invalid input");
		}
		return new ResponseEntity<Cylinder>(cylinderService.deleteCylinder(cylinderId), HttpStatus.OK);
	}

	@GetMapping("/get-all-cylinder-by-type/{type}")
	public ResponseEntity<List<Cylinder>> viewCylinderByType(@PathVariable String type) throws Exception {
		if (type == null && type == "") {
			throw new Exception("Invalid Input or null values cought");
		}
		return ResponseEntity.ok(cylinderService.viewCylinderByType(type));

	}

}
