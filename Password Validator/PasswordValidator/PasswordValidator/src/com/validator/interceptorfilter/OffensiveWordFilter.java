package com.validator.interceptorfilter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OffensiveWordFilter implements Filter{
	private String password = "";
	//The list contains a set of words that the system considers offensive 
	private static List<String> swearWords = new ArrayList<String>();
	private static String defaultFileLocation = new File("").getAbsolutePath();
	
	
	static {
			BufferedReader br = null;
			try {
				br = new BufferedReader(new FileReader(defaultFileLocation+"/src/bad-words.txt"));
			while(br.readLine() != null) {
				String line = br.readLine();
				swearWords.add(line);
			}
			br.close();
			} catch (Exception e) {
			e.printStackTrace();
			}
			
	}
	
	  
	@Override
	public String executeFilter(String password) {
		this.password = password;
		for(String swearWord : swearWords) {
			if(swearWord != null) {
				Pattern pat = Pattern.compile(swearWord, Pattern.CASE_INSENSITIVE);
		        Matcher mat = pat.matcher(password);
		        if(mat.find()) {
		        	return "found";
		        }
			}
			
	    }
        return password;
	}

}

    