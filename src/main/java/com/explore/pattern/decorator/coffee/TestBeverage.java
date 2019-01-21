package com.explore.pattern.decorator.coffee;

import org.junit.Test;

public class TestBeverage {
	
	@Test
	public void test(){
		
		Beverage espresso = new Espresso();
		System.out.println(espresso.getDescription()+"\t"+espresso.cost());
		
		espresso = new Milk(espresso);
		System.out.println(espresso.getDescription()+"\t"+espresso.cost());
		
		espresso = new Mocha(espresso);
		System.out.println(espresso.getDescription()+"\t"+espresso.cost());
	}
	
	public static void main(String[] args) {
		 	String str = "abc";  
	        String str1 = "abc";  
	        String str2 = new String("abc");  
	        System.out.println(str == str1);   //true
	        System.out.println(str1 == "abc"); //true
	        System.out.println(str2 == "abc"); //false
	        System.out.println(str1 == str2);  //false
	        System.out.println(str1.equals(str2));  //true
	        System.out.println(str1 == str2.intern());  //true
	        System.out.println(str2 == str2.intern());  //false
	        System.out.println(str1.hashCode() == str2.hashCode());  //true
	        
	}
	
}
