package com.adidas.poc.review.repository;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.adidas.poc.review.model.Review;

@Repository
public interface ReviewRepo extends MongoRepository<Review, String> {

	public List<Review> findByProductId(String productId);

}