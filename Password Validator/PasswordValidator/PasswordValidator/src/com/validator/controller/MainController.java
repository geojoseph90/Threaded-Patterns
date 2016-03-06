package com.validator.controller;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.validator.interceptorfilter.EasyGuessFilter;
import com.validator.interceptorfilter.Filter;
import com.validator.interceptorfilter.FilterManager;
import com.validator.interceptorfilter.OffensiveWordFilter;
import com.validator.interceptorfilter.SaltPasswordFilter;
import com.validator.model.TargetModel;
import com.validator.view.View;

public class MainController {
	private static View jFrame = new View("Password Validator"); 
	private static Map<String,Filter> filterMap = new HashMap<String,Filter>();
	static {
		filterMap.put("Offensive word", new OffensiveWordFilter());
		filterMap.put("Easy guess", new EasyGuessFilter());
		filterMap.put("Salt password", new SaltPasswordFilter());
	}
	public void validatePassword(String password, String filterOrder[]) {
	
		FilterManager manager = new FilterManager(new TargetModel(password));
		for(int i=0;i<filterOrder.length;i++) {
			Filter filter = filterMap.get(filterOrder[i].trim());
			manager.setFilter(filter);
		}
		if(manager.filterPassword(password)) {
			jFrame.setResultLabel(true);			
		}else{
			jFrame.setResultLabel(false);
		}
	}
	
	
	
	public static void main(String ags[]) {
		SwingUtilities.invokeLater(new Runnable() {
	        @Override
	        public void run() {               
	            jFrame.setSize(500,400);
	    		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);                  
	    		jFrame.setVisible(true);
	        }
	    });  
		
	}	
}
