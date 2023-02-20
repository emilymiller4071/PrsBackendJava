package com.maxtrain.prsspringboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.maxtrain.prsspringboot.entities.Vendor;

public interface VendorRepository extends JpaRepository<Vendor, Integer> {

}
