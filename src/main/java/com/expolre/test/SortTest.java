package com.expolre.test;

import org.junit.Test;

/**
 * ��������
 * @author lengyul
 *
 */
public class SortTest {

	@Test
	public void test1(){
		int nums [] =new int[]{122,213,23,111,66,88};
		//122,213,23,111,66
		//122,23,213,111,66
		//122,23,111,213,66
		//122,23,111,66,213
		
		//122,23,111,66   ,213
		//23,122,111,66
		//23,111,122,66
		//23,111,66,122
		
		//23,111,66        ,122,213
		//23,111,66		   ,122,213
		//23,66,111		   ,122,213
		
		//23,66,	    111,122,213
		//23,66
		/**
		 * ð������
		 */
		 /*for (int i = 0; i < nums.length; i++) {
			 for (int j = 0; j < nums.length - 1 - i; j++) {
				 int temp;
				 if (nums[j] > nums[j+1]) {
					 temp = nums[j+1];
					 nums[j+1] = nums[j];
					 nums[j]  = temp;
				}
			}
		 }*/
		 
		/**
		 * ѡ������
		 */ 
		for (int i = 0; i < nums.length; i++) {
			int index = i;
			int temp;
			for (int j = i + 1; j < nums.length; j++) {
				
			}
		}
		
		
		
		for (int i = 0; i < nums.length; i++) {
			System.out.println(nums[i]);
		}
		
	}
}
