package com.adidas.poc.review.datafetcher;

import java.util.HashMap;

import com.adidas.poc.review.exceptions.EntityNotFoundException;


public interface ProductDataFetch {

	public HashMap getProduct(String productId) throws EntityNotFoundException;
}
