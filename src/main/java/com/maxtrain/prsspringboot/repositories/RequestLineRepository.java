package com.maxtrain.prsspringboot.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.maxtrain.prsspringboot.entities.Request;
import com.maxtrain.prsspringboot.entities.RequestLine;

public interface RequestLineRepository extends JpaRepository<RequestLine, Integer> {

	List<RequestLine> findByRequest(Request request);
}
