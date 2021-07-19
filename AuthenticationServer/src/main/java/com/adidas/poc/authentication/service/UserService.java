package com.adidas.poc.authentication.service;

import com.adidas.poc.authentication.model.UserProfile;

public interface UserService {

	UserProfile addUser(UserProfile userprofile);
	boolean validateUser(UserProfile userprofile);
}
