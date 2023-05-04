package com.maxtrain.prsspringboot.controllers;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.maxtrain.prsspringboot.entities.Request;
import com.maxtrain.prsspringboot.repositories.RequestRepository;

@RestController
@RequestMapping("/requests")
@CrossOrigin(origins="http://localhost:4200")
public class RequestController {
	
	private final String NEW = "New";
	private final String REVIEW = "Review";
	private final String APPROVED = "Approved";
	private final String REJECTED = "Rejected";
	private final String REOPENED = "Reopened";
	
	@Autowired
	RequestRepository requestRepo;
	
	@GetMapping("")
	public List<Request> getAll(){
		List<Request> requests = requestRepo.findAll();
		
		return requests;
	}
	
	@GetMapping("/{id}")
	public Request getById(@PathVariable int id) {
		Request request = new Request();
		Optional<Request> optionalRequest = requestRepo.findById(id);
		
		if (optionalRequest.isPresent()) {
			request = optionalRequest.get();
		}
		
		return request;
}
	
	@PostMapping("")
	public Request create(@RequestBody Request newRequest) {
		Request request = new Request();
		
		boolean requestExists = requestRepo.existsById(newRequest.getId());
		
		if(!requestExists) {
			request.setStatus(NEW);
			request.setSubmittedDate(LocalDateTime.now());
			
			request = requestRepo.save(newRequest);
		}
		return request;
	}

	@PutMapping
	public Request update(@RequestBody Request updatedRequest) {
		Request request = new Request();
		
		boolean requestExists = requestRepo.findById(updatedRequest.getId()).isPresent();
		
		if (requestExists) {
			request = requestRepo.save(updatedRequest);
		}
		return request;
	}
	
	@DeleteMapping("/{id}")
	public Request delete(@PathVariable int id) {
		Request request = new Request();
		Optional<Request> optionalRequest = requestRepo.findById(id);
		boolean requestExists = optionalRequest.isPresent();
		
		if (requestExists) {
			request = optionalRequest.get();
			requestRepo.deleteById(id);
		}
		return request;
	}
	
	@GetMapping("/list-review/{userId}")
	public List<Request> getAllForReview(@PathVariable int userId){
		List<Request> requests = requestRepo.findByStatusAndUserIdNot(REVIEW, userId);
		
				
		return requests;
	}
	
	@PutMapping("/approve")
	public Request approve(@RequestBody Request approvedRequest) {
		Request request = new Request();
		boolean requestExists = requestRepo.existsById(approvedRequest.getId());
		
		if (requestExists) {
			approvedRequest.setStatus(APPROVED);
			
			request = requestRepo.save(approvedRequest);
		}
		
		return request;
	}
	
	@PutMapping("/review")
	public Request submitForReview(@RequestBody Request requestForReview) {
		Request request = new Request();
		boolean requestExists = requestRepo.existsById(requestForReview.getId());
		
		if(requestExists && request.getTotal() <= 50) {
			requestForReview.setStatus(APPROVED);
			requestForReview.setSubmittedDate(LocalDateTime.now());
			request = requestRepo.save(requestForReview);
			
		}else if (requestExists && request.getTotal() > 50) {
			requestForReview.setStatus(REVIEW);
			requestForReview.setSubmittedDate(LocalDateTime.now());
			request = requestRepo.save(requestForReview);
		}
			return request;
	}
	
	@PutMapping("/reject")
	public Request rejectRequest(@RequestBody Request rejectedRequest) {
		Request request = new Request();
		boolean requestExists = requestRepo.existsById(rejectedRequest.getId());
		
		if (requestExists) {
			rejectedRequest.setStatus(REJECTED);
			request = requestRepo.save(rejectedRequest);
		}
		return request;
	}
	
	@PutMapping("/reopen")
	public Request reopenRequest(@RequestBody Request reopenedRequest) {
		Request request = new Request();
		boolean requestExists = requestRepo.existsById(reopenedRequest.getId());
		
		if (requestExists) {
			reopenedRequest.setStatus(REOPENED);
			request = requestRepo.save(reopenedRequest);
		}
		return request;
	}
	
}

