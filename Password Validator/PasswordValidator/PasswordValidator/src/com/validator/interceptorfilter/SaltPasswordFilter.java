package com.validator.interceptorfilter;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

public class SaltPasswordFilter implements Filter{
	
	private static final Random RANDOM_GEN = new SecureRandom();
	
	@Override
	public String executeFilter(String password) {
		return saltIt(password);
	}
	
	//this method takes in a password and salt it using md5 to get a salted password
	public String saltIt(String password) {
		String salted_pwd = null;
		if(null == password) return null;
		
		try {
			MessageDigest digest = MessageDigest.getInstance("MD5");
			digest.update(password.getBytes(), 0, password.length());
			salted_pwd = new BigInteger(1, digest.digest()).toString(16);
		} catch (NoSuchAlgorithmException e) {
			salted_pwd="failed";
		}
		return salted_pwd;
	}

}
