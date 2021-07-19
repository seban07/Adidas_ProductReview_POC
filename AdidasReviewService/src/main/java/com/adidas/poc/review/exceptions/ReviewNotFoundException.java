package com.adidas.poc.review.exceptions;

public class ReviewNotFoundException extends Exception
{
	
	private static final long serialVersionUID = 1L;

	public ReviewNotFoundException(String msg)
	{
		super(msg);
	}

}