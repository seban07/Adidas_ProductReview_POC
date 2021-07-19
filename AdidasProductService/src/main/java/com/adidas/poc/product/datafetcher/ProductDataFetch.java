package com.adidas.poc.product.datafetcher;

import java.util.HashMap;

import com.adidas.poc.product.exceptions.EntityNotFoundException;


public interface ProductDataFetch {

	public HashMap getProduct(String productId) throws EntityNotFoundException;
}
