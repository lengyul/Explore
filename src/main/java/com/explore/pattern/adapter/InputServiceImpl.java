package com.explore.pattern.adapter;

public class InputServiceImpl implements InputService {

	@Override
	public String toUpperCaseStr(String str) {
		
		return str.toUpperCase();
	}

}
