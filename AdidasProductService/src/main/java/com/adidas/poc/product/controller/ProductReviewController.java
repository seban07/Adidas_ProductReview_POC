package com.adidas.poc.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.adidas.poc.product.exceptions.ConfigurationException;
import com.adidas.poc.product.exceptions.EntityNotFoundException;
import com.adidas.poc.product.model.ProductReviews;
import com.adidas.poc.product.service.ProductReviewService;

@RestController
@RequestMapping("/api/product")
public class ProductReviewController {

	@Autowired
	private ProductReviewService prService;
	
	@GetMapping("/findReviewByProductID/{id}")
	public ResponseEntity<?> findReviewByProductID(@PathVariable String id) throws EntityNotFoundException, ConfigurationException{
		
		var prodData = prService.findReviewsByProductId(id);
		if (prodData != null ) {
			return new ResponseEntity<ProductReviews>(prodData, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Invalid productID", HttpStatus.NOT_ACCEPTABLE);
		}

	}

}
