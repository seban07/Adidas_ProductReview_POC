package com.adidas.poc.review.service;

import java.util.List;

import com.adidas.poc.review.exceptions.EntityNotFoundException;
import com.adidas.poc.review.model.Review;


public interface ReviewService {

	public List<Review> showAllReviews();
	public Review addReview(Review review) throws EntityNotFoundException;
	public Review updateReview(Review review, String reviewId) throws EntityNotFoundException;
	public Boolean deleteReview(String reviewId) throws EntityNotFoundException;

}
