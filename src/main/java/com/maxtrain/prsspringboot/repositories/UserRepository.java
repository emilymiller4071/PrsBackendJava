package com.maxtrain.prsspringboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.maxtrain.prsspringboot.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	User findByUserNameAndPassword(String userName, String password);

	
	
}
