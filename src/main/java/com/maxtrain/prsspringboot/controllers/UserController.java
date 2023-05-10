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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.maxtrain.prsspringboot.entities.User;
import com.maxtrain.prsspringboot.repositories.UserRepository;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins="http://localhost:4200", allowedHeaders = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
public class UserController {

	@Autowired
	UserRepository userRepo;
	
	@GetMapping("")
	public List<User> getAll() {
		List<User> users = userRepo.findAll();
		
		return users;
	}
	
	
	@GetMapping("/{id}")
	public User getById(@PathVariable int id) {
		User user = new User();
		Optional<User> optionalUser = userRepo.findById(id);
		
		if (optionalUser.isPresent()) {
			user = optionalUser.get();
		} else {
	        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
		}
		return user;
	}
	
	
	@PostMapping("")
	public User create(@RequestBody User newUser) {
		User user = new User();
		
		boolean userExists = userRepo.findById(newUser.getId()).isPresent();
		
		if(!userExists) {
			user = userRepo.save(newUser);
		}
		return user;
	}
	
	
	@PutMapping("")
	public User update(@RequestBody User updatedUser) {
		User user = new User();
		
		boolean userExists = userRepo.findById(updatedUser.getId()).isPresent();
		
		if(userExists) {
			user = userRepo.save(updatedUser);
		} else {
	        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
		}		
		return user;
	}
	
	
	@DeleteMapping("/{id}")
	public User delete(@PathVariable int id) {
		User user = new User();
		Optional<User> optionalUser = userRepo.findById(id);
		boolean userExists = optionalUser.isPresent();
		
		if (userExists) {
		user = optionalUser.get();
		userRepo.deleteById(id);
		} else {
	        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
		}
		return user;
		}



	@PostMapping("/login")
	public User authenticate(@RequestBody User loginUser) {
		User user = userRepo.findByUsernameAndPassword(loginUser.getUsername(), loginUser.getPassword());
		
		if (user == null) {
			throw new ResponseStatusException(HttpStatus.I_AM_A_TEAPOT, "May be short and stout...");
		}
		
		return user;
	}
}


