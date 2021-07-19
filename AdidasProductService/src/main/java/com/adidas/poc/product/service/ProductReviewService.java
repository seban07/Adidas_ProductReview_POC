package com.adidas.poc.product.service;

import com.adidas.poc.product.exceptions.EntityNotFoundException;
import com.adidas.poc.product.model.ProductReviews;

public interface ProductReviewService {
	
	public ProductReviews findReviewsByProductId(String productId) throws EntityNotFoundException;

}
