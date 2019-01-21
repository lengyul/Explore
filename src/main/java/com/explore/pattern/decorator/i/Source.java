package com.explore.pattern.decorator.i;

public class Source implements Sourcable {

	@Override
	public void print() {
		
		System.out.println("source print.....");
	}
	
	@Override
	public String print(String str) {

		return str;
	}

	

}
