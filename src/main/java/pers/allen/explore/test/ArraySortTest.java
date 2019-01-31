package pers.allen.explore.test;

import org.junit.Test;

/**
 * 数组排序
 * @author lengyul
 *
 */
public class ArraySortTest {

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
		 * 冒泡排序
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
		 * 插入排序
		 */ 
		for (int i = 0; i < nums.length; i++) {
			int index = i;
			int temp = 0;
			for (int j = i + 1; j < nums.length; j++) {
				if (nums[index] > nums[j]) {
					index = j;
				}
			}
			temp = nums[i];
			nums[i] = nums[index];
			nums[index] = temp;
		}
		
		for (int i = 0; i < nums.length; i++) {
			System.out.println(nums[i]);
		}
		
	}
}
