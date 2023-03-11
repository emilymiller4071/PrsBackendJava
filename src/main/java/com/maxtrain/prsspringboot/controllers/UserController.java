package com.maxtrain.prsspringboot.controllers;


import java.util.List;
import java.util.Optional;

import com.maxtrain.prsspringboot.entities.requests.AuthenticateRequest;
import com.maxtrain.prsspringboot.entities.responses.AuthenticateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maxtrain.prsspringboot.entities.User;
import com.maxtrain.prsspringboot.repositories.UserRepository;

@RestController
@RequestMapping("/users")
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
		}
		return user;
		}	
	
		
	@PostMapping("/login")
	public AuthenticateResponse authenticate(@RequestBody AuthenticateRequest loginUser) {
		User user = userRepo.findByUserNameAndPassword(loginUser.getUserName(), loginUser.getPassword());

		return new AuthenticateResponse(
				user.getId(),
				user.getFirstName(),
				user.getLastName(),
				user.isReviewer(),
				user.isAdmin()
		);
	}
}
