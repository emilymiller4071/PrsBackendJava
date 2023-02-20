package com.maxtrain.prsspringboot.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.maxtrain.prsspringboot.entities.Request;

public interface RequestRepository extends JpaRepository<Request, Integer> {

	List<Request> findByStatusAndUserIdNot (String status, int userID);

	
	
}
