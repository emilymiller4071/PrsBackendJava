package com.maxtrain.prsspringboot.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.maxtrain.prsspringboot.entities.Vendor;
import com.maxtrain.prsspringboot.repositories.VendorRepository;


@RestController
@RequestMapping("/vendors")
@CrossOrigin(origins="http://localhost:4200", allowedHeaders = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
public class VendorController {
	
	@Autowired
	VendorRepository vendorRepo;
	
	@GetMapping("")
	public List<Vendor> getAll() {
		List<Vendor> vendors = vendorRepo.findAll();
		
		return vendors;
	}
	
	
	@GetMapping("/{id}")
	public Vendor getById(@PathVariable int id) {
		Vendor vendor = new Vendor();
		Optional<Vendor> optionalVendor = vendorRepo.findById(id);
		
		if (optionalVendor.isPresent()) {
			vendor = optionalVendor.get();
		} else {
	        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Vendor not found");
		}
	
	return vendor;
}

	@PostMapping("")
	public Vendor createVendor(@RequestBody Vendor newVendor) {
		Vendor vendor = vendorRepo.save(newVendor);
	
		boolean vendorExists = vendorRepo.findById(newVendor.getId()).isPresent();
		
		if(!vendorExists) {
			vendor = vendorRepo.save(newVendor);
		}
		
		return vendor;
	}

	@PutMapping
	public Vendor update(@RequestBody Vendor updatedVendor) {
		Vendor vendor = new Vendor();
		
		boolean vendorExists = vendorRepo.findById(updatedVendor.getId()).isPresent();
		
		if (vendorExists) {
			vendor = vendorRepo.save(updatedVendor);
		} else {
	        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Vendor not found");
		}
		return vendor;
	}
	
	@DeleteMapping("/{id}")
	public Vendor delete(@PathVariable int id) {
		Vendor vendor = new Vendor();
		Optional<Vendor> optionalVendor = vendorRepo.findById(id);
		boolean vendorExists = optionalVendor.isPresent();
		
		if (vendorExists) {
			vendor = optionalVendor.get();
			vendorRepo.deleteById(id);
		} else {
	        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Vendor not found");
		}
		return vendor;
	}
}

