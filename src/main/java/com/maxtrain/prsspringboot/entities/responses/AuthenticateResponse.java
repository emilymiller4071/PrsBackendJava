package com.maxtrain.prsspringboot.entities.responses;


import org.springframework.stereotype.Component;


@Component
public class AuthenticateResponse {
    private int id;
    private String firstName;
    private String lastName;
    private boolean isReviewer;
    private boolean isAdmin;
    
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public boolean isReviewer() {
		return isReviewer;
	}
	public void setReviewer(boolean isReviewer) {
		this.isReviewer = isReviewer;
	}
	public boolean isAdmin() {
		return isAdmin;
	}
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	public AuthenticateResponse() {
	}
	public AuthenticateResponse(int id, String firstName, String lastName, boolean isReviewer, boolean isAdmin) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.isReviewer = isReviewer;
		this.isAdmin = isAdmin;
	}
    
    
    

   
}
