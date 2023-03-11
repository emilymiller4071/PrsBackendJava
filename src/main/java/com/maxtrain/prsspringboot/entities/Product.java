package com.maxtrain.prsspringboot.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Products")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String partNbr;
	private String name;
	private double price;
	private String unit;
	private String photoPath;
	private int vendorID;
	
	@ManyToOne
	@JoinColumn(name = "VendorID")
	private Vendor vendor;

	public Product() {
	}


	public Product(int id, String partNbr, String name, double price, String unit, String photoPath, Vendor vendor) {
		this.id = id;
		this.partNbr = partNbr;
		this.name = name;
		this.price = price;
		this.unit = unit;
		this.photoPath = photoPath;
		this.vendorID = vendorID;
		this.vendor = vendor;
		
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPartNbr() {
		return partNbr;
	}

	public void setPartNbr(String partNbr) {
		this.partNbr = partNbr;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getPhotoPath() {
		return photoPath;
	}

	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}

	
	public Vendor getVendor() {
		return vendor;
	}


	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}


	public int getVendorID() {
		return vendorID;
	}


	public void setVendorID(int vendorID) {
		vendorID = vendorID;
	}


	@Override
	public String toString() {
		return "Product [id=" + id + ", partNbr=" + partNbr + ", name=" + name + ", price=" + price + ", unit=" + unit
				+ ", photoPath=" + photoPath + ", VendorID=" + vendorID + ", vendor=" + vendor + "]";
	}


//	@Override
//	public String toString() {
//		return "Product [id=" + id + ", partNbr=" + partNbr + ", name=" + name + ", price=" + price + ", unit=" + unit
//				+ ", photoPath=" + photoPath + ", vendor=" + vendor + "]";
//	}

	
}
