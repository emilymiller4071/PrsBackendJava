package com.maxtrain.prsspringboot.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Requests")
public class Request {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String description;
	
	private String justification;
	
	private String rejectionReason;
	
	private String deliveryMode;
	
	private LocalDateTime submittedDate;
	
	private LocalDate dateNeeded;
	
	private String status;
	
	private double total;
	
	@ManyToOne
	@JoinColumn(name = "UserID")
	private User user;

	
	public Request() {
	}

	public Request(int id, String description, String justification, String rejectionReason, String deliveryMode,
			LocalDateTime submittedDate, LocalDate dateNeeded, String status, double total, User user) {
		this.id = id;
		this.description = description;
		this.justification = justification;
		this.rejectionReason = rejectionReason;
		this.deliveryMode = deliveryMode;
		this.submittedDate = submittedDate;
		this.dateNeeded = dateNeeded;
		this.status = status;
		this.total = total;
		this.user = user;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getJustification() {
		return justification;
	}

	public void setJustification(String justification) {
		this.justification = justification;
	}

	public String getRejectionReason() {
		return rejectionReason;
	}

	public void setRejectionReason(String rejectionReason) {
		this.rejectionReason = rejectionReason;
	}

	public String getDeliveryMode() {
		return deliveryMode;
	}

	public void setDeliveryMode(String deliveryMode) {
		this.deliveryMode = deliveryMode;
	}

	public LocalDateTime getSubmittedDate() {
		return submittedDate;
	}

	public void setSubmittedDate(LocalDateTime submittedDate) {
		this.submittedDate = submittedDate;
	}

	public LocalDate getDateNeeded() {
		return dateNeeded;
	}

	public void setDateNeeded(LocalDate dateNeeded) {
		this.dateNeeded = dateNeeded;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	


	@Override
	public String toString() {
		return "Request [id=" + id + ", description=" + description + ", justification=" + justification
				+ ", rejectionReason=" + rejectionReason + ", deliveryMode=" + deliveryMode + ", submittedDate="
				+ submittedDate + ", dateNeeded=" + dateNeeded + ", status=" + status + ", total=" + total + ", user="
				+ user + "]";
	}


	
	
}