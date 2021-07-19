package com.adidas.poc.review.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adidas.poc.review.exceptions.EntityNotFoundException;
import com.adidas.poc.review.model.Review;
import com.adidas.poc.review.service.ReviewService;

@RestController
@RequestMapping("/api/review")
public class ReviewController {

	@Autowired
	private ReviewService service;
	
	
	@GetMapping("/viewAll")
	public ResponseEntity<?> getAllReviews()
	{
		List<Review> productReviews = service.showAllReviews();
		return new ResponseEntity<List>(productReviews, HttpStatus.OK);
	}
	
	@PostMapping("/addReview")
	public ResponseEntity<?> addReview(@RequestBody Review review) throws EntityNotFoundException 
	{	
		Review reviewAdded = service.addReview(review);
		if (null != reviewAdded)
			return new ResponseEntity<Review>(reviewAdded, HttpStatus.OK);
		else
			return new ResponseEntity<String>("Review not added - Product not found for ID : " + review.getProductId(), HttpStatus.NOT_ACCEPTABLE);			
	}
	
	@PutMapping("/updateReview/{id}")
	public ResponseEntity<?> updateReview(@RequestBody Review review, @PathVariable String id)
			throws EntityNotFoundException {
		Review reviewUpdated = service.updateReview(review, id);
		return new ResponseEntity<Review>(reviewUpdated, HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteReview/{id}")
	public ResponseEntity<?> deleteReview(@PathVariable String id) throws EntityNotFoundException 
	{		
		var result = service.deleteReview(id);
		if (result != null && result) {
			return new ResponseEntity<String>("Review recod deleted " + id, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Invalid Review Id " + id, HttpStatus.NOT_ACCEPTABLE);
		}
		
	}
	
	
}
