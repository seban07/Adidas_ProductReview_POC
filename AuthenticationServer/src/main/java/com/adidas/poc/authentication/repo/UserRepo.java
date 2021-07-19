package com.adidas.poc.authentication.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.adidas.poc.authentication.model.UserProfile;

@Repository
public interface UserRepo extends MongoRepository<UserProfile,String> {
	
	UserProfile findByUseridAndPassword(String userid,String password);

}
