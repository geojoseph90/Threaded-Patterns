package com.validator.model;

import com.validator.interceptorfilter.Filter;

public class TargetModel {
	String user_password = null;
	
	public TargetModel(String password) {
		user_password = password;
	}
	
	public void setPassword(String password) {
		user_password = password;
		System.out.println("Password saved");
	}

	
}
