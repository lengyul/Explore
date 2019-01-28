package com.explore.test;

import org.junit.Test;

/**
 * 数组中快速查找某个值
 * @author lengyul
 *
 */
public class ArrayFindValueTest {
	
	
public int binarySearchValue(int[] array,int number){
		
		int high = array.length - 1;
		int low = 0;
		
		while (low <= high) {
			int middle = (low + high) / 2;
			if (array[middle] > number) {
				high = middle - 1;
			}else if(array[middle] < number){
				low  = middle + 1;
			}else{
				return middle;
			}
			
		}
		return -1;
	}
	
	@Test
	public void testBinarySearchValue(){
		int number = 5;
		int [] array = {1,3,5,7,9,15,20,35};
		int reuslt = binarySearchValue(array,number);
		System.out.println(reuslt);
	}
	
	/**
	 * 在一个二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
	 * 输入这样的一个二维数组和一个整数，判断数组中是否含有该整数
	 * @param array
	 * @param number
	 * @return
	 */
	public boolean doubleArrayFindValue(int[][] array,int number){
		//从数组右上角或者左下角开始查找
		/*
		 * 
		 * 1.右上角查找：将当前列最右边的数作为一个点，如果number比它小，那么number这个值有可能在同行左侧
		 * ，如果number比它大，就从下一列开始重新查找。
		 * 
		 * 2.左下角查找：将当前列最左边的数作为一个点，如果number比它小，就从上一列开始重新查找4
		 * ，如果number比它大，那么number这个值有可能在同行右侧。
		 */
		//右上角查找
		/*int x = 0,y = array[0].length - 1; 
		while (x < array.length && y >= 0) {
			if (number > array[x][y]) {
				x ++;
			}else if(number < array[x][y] ){
				y -- ;
			}else{
				return true;
			}	
		}*/
		
		//左下角查找
		int x = array.length - 1,y = 0;
		while (x >= 0 && y < array[0].length) {
			if (number > array[x][y]) {
				y ++ ;
			}else if(number < array[x][y]){
				x --;
			}else{
				return true;
			}
		}
		return false;
	}
	
	@Test
	public void testDoubleArrayFindValue(){
		int number = 42;
		int arr[][] = { 
						 {8,11,13,20,23}
						,{9,14,16,22,25}
						,{21,23,26,27,29}
						,{33,36,38,40,45}
						,{34,37,42,44,60}
						};
		boolean result = doubleArrayFindValue(arr,number);
		System.out.println(result);
	}
	
	
	/**
	 * 给定一个整数数组nums，我们将pivot索引定义为索引，其中索引左边的数字之和等于索引右边的数字之和。
	 * 输入： nums = [1,7,3,6,5,6]
	 * 输出： 3
	 * 说明： 
	 * 索引3左侧的数字之和（nums [3] = 6）等于索引3右侧的数字之和。
	 * 没有索引满足问题陈述中的条件返回 -1
	 * @param array
	 * @return
	 */
	public int findPivotIndex(int [] array){
		/*
		 * 假设索引位置为 x，计算出当前索引之前所有索引的和为sum，根据sum * 2 == total(数组总和) - 当前索引x的值
		 * 如果条件成立，则当前x索引的位置为中枢点，返回 x；否则继续遍历计算，遍历结束后还没返回，说明没有中枢点返回-1。 
		 */
		int total = 0,sum = 0;
		for (int i = 0; i < array.length; i++) total+=array[i];	//计算数组总和
		for (int i = 0; i < array.length; sum+=array[i++]) {
			if (sum * 2 == total - array[i]) {
				return i;
			}
		}
		return -1;
	}
	
	@Test
	public void testFindPivotIndex(){
		
		int [] array =new int[]{1,3,8,6,6,6};
		
		int result = findPivotIndex(array);
		System.out.println(result);
	}
	
	
	/**
	 * 在给定的整数数组中nums，始终只有一个最大的元素。
	 * 查找数组中的最大元素是否至少是数组中每个其他数字的两倍。
	 * 输入： nums = [3,6,1,0]
 	 * 输出： 1
 	 * 说明： 6是最大整数，对于数组x中的每个其他数字，
	 * 6是x的两倍多。值6的索引是1，所以我们返回1。
	 * 如果是，则返回最大元素的索引，否则返回-1。	
	 * @param array
	 * @return
	 */
	public int findMaxNumberAtTwiceOfOthers(int[] array){
		/*
		 * 1.找出最大的两个值
		 * 2.计算最大的值和第二大的值进行比较
		 * (max1 >= max2 * 2)如果条件成立那么max1至少是所有的索引值的两倍，返回最大值的索引，否则返回-1
		 */
		 int max1 = 0,max2 = 0,indexOfMax = 0;
		 for (int i = 0; i < array.length; i++) {
			 if (array[i] > max1) {
				max2 = max1; 
				max1 = array[i];
				indexOfMax = i;
			}else if(array[i] > max2){
				max2 = array[i];
			} 
		}
		return max1 >= max2 * 2 ? indexOfMax : -1;
	}
	
	@Test
	public void testFindMaxNumberAtTwiceOfOthers(){	
		int array [] = {1,32,4,66,50,99,18,77,88};
		int result = findMaxNumberAtTwiceOfOthers(array);
		System.out.println(result);
	}
	
	
	@Test
	public void testPlusOne(){
		int [] array = {9,9,9,8};
		int [] plusa = plusOne(array);
		for (int i = 0; i < plusa.length; i++) {
			System.out.print(plusa[i]);
		}	
	}		 

	/**
	 * 给定表示非负整数的非空数字数组，加上整数的1。
	 * 输入： [1,2,3]
 	 * 输出： [1,2,4]
 	 * 说明：数组表示整数123 
	 * @param array
	 * @return
	 */
	public int[] plusOne(int[] array) {
		/*
		 * 获取数组最后一位数字大小(个位) 
		 * 如果数组中最后一位数字 < 9，直接 +1 返回
		 * > 9 将它赋值为0，向十位，百位...依次循环
		 * 当递进到数组的第一位数字(> 9 时，重新生成一个新数组长度加1，向前进位)
		 */
		int length = array.length -1 ;
		while (length >= 0) {
			if (array[length] < 9) {
				array[length]+=1;
				return array;
			}else{
				array[length]=0;
			}
			length --;
		}
		int [] newArray = new int[array.length + 1];
		newArray[0] = 1;
		return newArray;
	}
	
	
}
