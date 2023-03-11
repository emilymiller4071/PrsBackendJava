package com.maxtrain.prsspringboot.entities.requests;

import org.springframework.stereotype.Component;


@Component
public class AuthenticateRequest {
    private String userName;
    
    private String password;
    
    
	public String getUserName() {
		return userName;
	}
	
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
	public String getPassword() {
		return password;
	}
	
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	public AuthenticateRequest() {
	}
	
	public AuthenticateRequest(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}
	@Override
	public String toString() {
		return "AuthenticateRequest [userName=" + userName + ", password=" + password + "]";
	}



}
