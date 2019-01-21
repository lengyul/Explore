package com.explore.test;

public class StringFindNumberTest {
	
	public static void main(String[] args) {
		String str = "a1bc2e68";
		
		// [0-9] = [48,49,50,51,52,53,54,55,56,57]
		byte[] bytes = str.getBytes();
		for (int i = 0; i < bytes.length; i++) {
			if (bytes[i] >= 48 && bytes[i] <= 57) {				
				System.out.print((char)bytes[i]);
			}
		}
		// [0-9]
		char[] chars = str.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			if (chars[i] >= '0' && chars[i] <= '9') {
				System.out.println(chars[i]);
			}
		}
		
	}
}
