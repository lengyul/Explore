package com.explore.pattern.decorator.i;


public class SourceUpperCase implements Sourcable {

	Sourcable sourcable;
	
	public SourceUpperCase(Sourcable sourcable){
		this.sourcable = sourcable;
	}
	
	@Override
	public void print() {
		System.out.println("sourceUpperCase print.....");
	}
	
	@Override
	public String print(String str) {
		
		return sourcable.print(str).toUpperCase();
	}

	

}
