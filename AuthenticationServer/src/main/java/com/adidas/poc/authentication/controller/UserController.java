package com.adidas.poc.authentication.controller;

import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adidas.poc.authentication.model.UserProfile;
import com.adidas.poc.authentication.service.UserService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequestMapping("/api/user")
public class UserController {
	@Autowired
	UserService userservice;
	
	@PostMapping("/addUser")
	public ResponseEntity<?> addUser(@RequestBody UserProfile userprof)
	
	{
		
		UserProfile userprofileresult=userservice.addUser(userprof);
		return new ResponseEntity<UserProfile>(userprofileresult,HttpStatus.CREATED);
		
	}

	
	@PostMapping("/login")
	
	public ResponseEntity<?> checkUser(@RequestBody UserProfile userprofile)
	{
		boolean result= userservice.validateUser(userprofile);
		if(result)
		{
			String mytoken = generateToken(userprofile);
			HashMap<String,String>  mymap=new HashMap();
			mymap.put("token",mytoken);
			return new ResponseEntity<HashMap>(mymap,HttpStatus.ACCEPTED);
		
		}
		else
			 return new ResponseEntity<String>("Invalid Credentials",HttpStatus.FORBIDDEN);
	}
	
	
	private String generateToken(UserProfile userprof)
	{
		
	 long expirytime=10_000_000;
	 
	 return Jwts.builder().setSubject(userprof.getUserid())
			               .setIssuedAt(new Date(System.currentTimeMillis()))
			               .setExpiration(new Date(System.currentTimeMillis()+expirytime))
			               .signWith(SignatureAlgorithm.HS256,"myjwtkey")
			               .compact();
			            
			            
		
	}

}
