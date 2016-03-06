package com.validator.interceptorfilter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.validator.model.TargetModel;

public class FilterManager {
	private FilterChain filterChain;
	
	
	public FilterManager(TargetModel target) {
		filterChain = new FilterChain(target);
	}
	
	public void setFilter(Filter filter) {
		filterChain.addFilter(filter);
	}
	
	public boolean filterPassword(String password) {
		return filterChain.execute(password);
	}
	
}
