package com.maxtrain.prsspringboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.maxtrain.prsspringboot.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
