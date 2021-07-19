package com.adidas.poc.authentication.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class UserProfile {
	
@Id
String userid;
String password;
public String getUserid() {
	return userid;
}
public void setUserid(String userid) {
	this.userid = userid;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}







}
