package com.validator.interceptorfilter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.validator.model.TargetModel;

public class FilterChain {
	private List<Filter> filterList = new ArrayList<Filter>();
	
	private TargetModel target = null;
	
	public FilterChain(TargetModel target) {
		this.target = target;
	}
	
	public void addFilter(Filter filter) {
		filterList.add(filter);
	}
	
	public boolean execute(String password) {
		int counter = 0;
		String result = "";
		boolean isValid = true;
		for(Filter filter : filterList) {
			if(counter == 0) {
				result = filter.executeFilter(password);
			}
			else {
				result = filter.executeFilter(result);
			}
			if(result.equals("found") || result.equals("failed") || result.equals("")) {
				isValid = false;
				break;
			}
			counter ++;
		}
		
		
		if(isValid) {
			target.setPassword(password);
			
		}
		else {
			// do nothing
		}
		return isValid;
	}
	
	
}
