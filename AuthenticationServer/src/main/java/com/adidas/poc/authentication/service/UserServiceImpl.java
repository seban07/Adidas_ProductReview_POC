package com.adidas.poc.authentication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adidas.poc.authentication.model.UserProfile;
import com.adidas.poc.authentication.repo.UserRepo;


@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserRepo userrepo;
	
	@Override
	public UserProfile addUser(UserProfile userprofile) {
		return  userrepo.save(userprofile);
		
	}

	@Override
	public boolean validateUser(UserProfile userprofile) {
 
		UserProfile userexist=userrepo.findByUseridAndPassword(userprofile.getUserid(), userprofile.getPassword());
		
		return null != userexist;
	}

}
