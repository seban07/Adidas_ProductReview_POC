package com.adidas.poc.review.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.adidas.poc.review.datafetcher.ProductDataFetch;
import com.adidas.poc.review.exceptions.EntityNotFoundException;
import com.adidas.poc.review.model.Review;
import com.adidas.poc.review.repository.ReviewRepo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Service
public class ReviewServiceImpl implements ReviewService {
	
	@Autowired
	private ReviewRepo repository;
	
	@Autowired
	private ProductDataFetch productDataFetch;

	@Override
	public List<Review> showAllReviews() {
		return repository.findAll();
	}

	@Override
	public Review addReview(Review review) throws EntityNotFoundException {
		productDataFetch.getProduct(review.getProductId());
		return repository.insert(review);
	}

	@Override
	public Review updateReview(Review review, String reviewId) throws EntityNotFoundException {
		productDataFetch.getProduct(review.getProductId());
		Optional<Review> findById = repository.findById(reviewId);
		if (findById.isPresent()) {
			findById.get().setDescription(review.getDescription());
			findById.get().setScore(review.getScore());
			findById.get().setTitle(review.getTitle());
			return repository.save(findById.get());
		} else {
			throw new EntityNotFoundException("Review with id = " + reviewId + " not found.");
		}
	}

	@Override
	public Boolean deleteReview(String reviewId) throws EntityNotFoundException {
		if (!StringUtils.hasText(reviewId)) {
			throw new EntityNotFoundException("Review ID not provided.");
		}
		Optional<Review> findById = repository.findById(reviewId);
		if (findById.isPresent()) {
			repository.deleteById(reviewId);
			return true;
		} else {
			throw new EntityNotFoundException("Review with id = " + reviewId + " not found.");
		}
	}
	
	public List<Review> findReviewsForProductId(String prodId) throws EntityNotFoundException {
		productDataFetch.getProduct(prodId);
		List<Review> findById = repository.findByProductId(prodId);
		if (findById.isEmpty()) {
			throw new EntityNotFoundException("Reviews for prodId = " + prodId + " not found.");
		} else {
			return findById;
		}
		
	}

}