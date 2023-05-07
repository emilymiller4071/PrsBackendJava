package com.maxtrain.prsspringboot.controllers;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.maxtrain.prsspringboot.entities.Request;
import com.maxtrain.prsspringboot.repositories.RequestRepository;

@RestController
@RequestMapping("/requests")
@CrossOrigin(origins="http://localhost:4200", allowedHeaders = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })

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
		} else {
	        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Request not found");
		}
		
		return request;
}
	
	@PostMapping("")
	public Request create(@RequestBody Request newRequest) {
//		Request request = new Request();
		
		boolean requestExists = requestRepo.existsById(newRequest.getId());
		
		if(!requestExists) {
			newRequest.setStatus(NEW);
			newRequest.setSubmittedDate(LocalDateTime.now());
			
			newRequest = requestRepo.save(newRequest);
		}
		return newRequest;
	}

	@PutMapping
	public Request update(@RequestBody Request updatedRequest) {
		Request request = new Request();
		
		boolean requestExists = requestRepo.findById(updatedRequest.getId()).isPresent();
		
		if (requestExists) {
			request = requestRepo.save(updatedRequest);
		} else {
	        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Request not found");
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
		} else {
	        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Request not found");
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
		} else {
	        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Request not found");
		}
		
		return request;
	}
	
	@PutMapping("/review")
	public Request submitForReview(@RequestBody Request requestForReview) {
	    Optional<Request> optionalRequest = requestRepo.findById(requestForReview.getId());
	    
	    if (optionalRequest.isPresent()) {
	        Request request = optionalRequest.get();
	        
	        if (requestForReview.getTotal() > 50) {
	            requestForReview.setStatus(REVIEW);
	        } else {
	            requestForReview.setStatus(APPROVED);
	        }
	        
	        requestForReview.setSubmittedDate(LocalDateTime.now());
	        request = requestRepo.save(requestForReview);
	        return request;
	    } else {
	        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Request not found");
		}  
	}
	
	
	@PutMapping("/reject")
	public Request rejectRequest(@RequestBody Request rejectedRequest) {
		Request request = new Request();
		boolean requestExists = requestRepo.existsById(rejectedRequest.getId());
		
		if (requestExists) {
			rejectedRequest.setStatus(REJECTED);
			request = requestRepo.save(rejectedRequest);
		} else {
	        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Request not found");
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
		} else {
	        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Request not found");
		}
		return request;
	}
	
}

