package com.onlinegasbooking.controller;

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

import com.onlinegasbooking.entity.SurrenderCylinder;
import com.onlinegasbooking.exceptions.ResourceNotFoundException;
import com.onlinegasbooking.service.IsurrenderCylinderService;

@RestController
@RequestMapping("/api/surrender-cylinder")
public class SurrenderCylinderController {

	@Autowired
	private IsurrenderCylinderService surrenderCylinderService;

	@PostMapping("/insert/{customerId}/{cylinderId}")
	public ResponseEntity<SurrenderCylinder> insertSurrenderCylinder(@RequestBody SurrenderCylinder sc,
			@PathVariable long customerId, @PathVariable long cylinderId) throws Exception {

		if (customerId == 0 && cylinderId == 0 && sc == null) {
			throw new Exception("Invalid Input Values");
		}
		return new ResponseEntity<SurrenderCylinder>(
				surrenderCylinderService.insertSurrenderCylinder(sc, customerId, cylinderId), HttpStatus.CREATED);
	}

	@PutMapping("/update")
	public ResponseEntity<SurrenderCylinder> updateSurrenderCylinder(@RequestBody SurrenderCylinder sc)
			throws Exception {
		if (sc == null) {
			throw new Exception("Invalid Input Values");
		}
		return new ResponseEntity<SurrenderCylinder>(surrenderCylinderService.updateSurrenderCylinder(sc),
				HttpStatus.OK);
	}

	@DeleteMapping("/delete")
	public ResponseEntity<SurrenderCylinder> deleteSurrenderCylinder(@RequestBody SurrenderCylinder sc)
			throws Exception {
		if (sc == null) {
			throw new Exception("Invalid Input Values");
		}
		return new ResponseEntity<SurrenderCylinder>(surrenderCylinderService.deleteSurrenderCylinder(sc),
				HttpStatus.OK);
	}

	@GetMapping("/get-total-count")
	public ResponseEntity<Integer> countSurrenderedCylinders() {
		return new ResponseEntity<Integer>(surrenderCylinderService.countSurrenderedCylinders(), HttpStatus.OK);
	}

}
