package com.adidas.poc.review.configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.adidas.poc.review.model.Review;
import com.adidas.poc.review.repository.ReviewRepo;

@Component
public class DatabaseInitializer {

	private static final Logger logger = LoggerFactory.getLogger(DatabaseInitializer.class);

	@Autowired
	private ReviewRepo reviewRepo;

	@PostConstruct
	public void init() {
		if (reviewRepo.findAll().isEmpty()) {
			logger.debug("Starting database seeding...");
			reviewRepo.insert(getReviews());
			logger.debug("Reviews inserted into the database.");
		}
	}

	public List<Review> getReviews() {
		List<Review> list = new ArrayList<>(0);
		list.add(Review.builder().productId("M20324").title("BlackShoe").description("good")
				.score(getRandomNumberBetween(0, 5)).build());
		list.add(Review.builder().productId("M20324").title("BlackShoe").description("avg")
				.score(getRandomNumberBetween(0, 5)).build());
		list.add(Review.builder().productId("M20324").title("BlackShoe").description("good")
				.score(getRandomNumberBetween(0, 5)).build());
		list.add(Review.builder().productId("AC7836").title("WhiteShoe").description("good")
				.score(getRandomNumberBetween(0, 5)).build());
		list.add(Review.builder().productId("AC7836").title("WhiteShoe").description("good")
				.score(getRandomNumberBetween(0, 5)).build());
		list.add(Review.builder().productId("B42000").title("GreyShoe").description("avg")
				.score(getRandomNumberBetween(0, 5)).build());
		list.add(Review.builder().productId("B42000").title("GreyShoe").description("avg")
				.score(getRandomNumberBetween(0, 5)).build());
		list.add(Review.builder().productId("B42000").title("GreyShoe").description("good")
				.score(getRandomNumberBetween(0, 5)).build());
		list.add(Review.builder().productId("B42000").title("GreyShoe").description("good")
				.score(getRandomNumberBetween(0, 5)).build());
		list.add(Review.builder().productId("BB5476").title("RedShow").description("avg")
				.score(getRandomNumberBetween(0, 5)).build());
		list.add(Review.builder().productId("C77154").title("BlueShoe").description("bad")
				.score(getRandomNumberBetween(0, 5)).build());

		return list;
	}

	public Integer getRandomNumberBetween(int minimum, int maximum) {
		Random rand = new Random();
		return minimum + rand.nextInt((maximum - minimum) + 1);
	}
}
