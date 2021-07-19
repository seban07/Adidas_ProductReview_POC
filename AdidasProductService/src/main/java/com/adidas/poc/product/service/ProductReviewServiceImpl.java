package com.adidas.poc.product.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.adidas.poc.product.exceptions.EntityNotFoundException;
import com.adidas.poc.product.model.ProductReviews;
import com.adidas.poc.product.model.Review;
import com.adidas.poc.product.repo.ReviewRepository;

@Service
public class ProductReviewServiceImpl implements ProductReviewService{
	
	@Autowired
	ReviewRepository reviewRepository;

	@Override
	public ProductReviews findReviewsByProductId(String productId) throws EntityNotFoundException {
		
		List<Review> reviewsByProductId = reviewRepository.findByProductId(productId);
		if (reviewsByProductId.isEmpty()) {
			throw new EntityNotFoundException("No reviews found for productId = " + productId);
		}
		return processReviews(productId, reviewsByProductId);
	}

	public ProductReviews processReviews(String productId, List<Review> reviewsByProductId) {
		if (!StringUtils.hasText(productId)) {
			throw new IllegalArgumentException("Invalid Product ID");
		}

		Double averageScore = reviewsByProductId.stream().filter(review -> productId.equals(review.getProductId()))
				.mapToDouble(Review::getScore).average().orElse(Double.valueOf(0));

		averageScore = round(averageScore, 2);

		long count = reviewsByProductId.stream().filter(review -> productId.equals(review.getProductId())).count();

		return ProductReviews.builder().productId(productId).quantity(count).averageScore(averageScore)
				.build();
	}

	private double round(double value, int places) {
		if (places < 0)
			throw new IllegalArgumentException();

		BigDecimal bd = BigDecimal.valueOf(value);
		bd = bd.setScale(places, RoundingMode.HALF_UP);
		return bd.doubleValue();
	}
}
