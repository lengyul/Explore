package com.explore.test;

import org.junit.Test;

/**
 * Java中的二进制运算
 * 
 * @author lengyul
 *
 */
public class BinarySystemCalcTest {

	/**
	 * 进制之间互相转换
	 */
	@Test
	public void test() {
		//十进制转换为其他进制
		System.out.println(Integer.toBinaryString(100)); //二进制
		System.out.println(Integer.toHexString(100));	//十六进制
		System.out.println(Integer.toOctalString(100)); //八进制
		
		//其他进制转换为十进制
		System.out.println(Integer.parseInt("1010",2)); //二进制
		System.out.println(Integer.parseInt("20",8));//八进制
		System.out.println(Integer.parseInt("A1",16));//十六进制
	}
	
	/**
	 * 负数用二进制表示
	 */
	public void test2(){
		 int number = 1;
		 //原码 00000000 00000000 00000000 00000001;
		 //反码 11111111 11111111 11111111 11111110;
		 //补码 11111111 11111111 11111111 11111111;	
		 System.out.println(Integer.toBinaryString(-1)); //二进制	
		 
	}
	
	
	/**
	 * int数据类型有：byte(8bit) short(16bit) int(32bit) long(64bit)
	 * float数据类型有：单精度(32bit float) 双精度(64bit double)
	 * boolean类型变量的取值有：true false 
	 * char数据类型有：Unicode字符 16位
	 * 对应的类类型：Integer Float Boolean Character Double Short Byte Long 
	 */
	@Test
	public void test3(){
		System.out.println(10 >> 1*8);
		/*
		 * &0xff可以将高的24位置为0，低8位保持原样。这样做的目的就是为了保证二进制数据的一致性。
		 * 数据类型转换为字节
		 * 8143(00000000 00000000 00011111 11001111)
		 * 第一个（低端）字节： 11001111 >> 0*8 & 0xff = (11001111)
		 * 第二个（低端）字节： 00011111 >> 1*8 & 0xff = (00011111)
		 * 第三个（低端）字节： 00000000 >> 2*8 & 0xff = (00000000)
		 * 第四个（低端）字节： 00000000 >> 2*8 & 0xff = (00000000)
		 */
		//1. 11001111 & 0xff => 11001111 & 11111111 = 11001111 = 207 - 256
		//2. 00011111 & 0xff => 00011111 & 11111111 = 00011111 = 31
		//3. 00000000 & 0xff => 00000000 & 11111111 = 00000000 = 0
		//4. 00000000 & 0xff => 00000000 & 11111111 = 00000000 = 0
		byte [] arr  = int2Bytes(8143);
		System.out.println(arr[0]+","+arr[1]+","+arr[2]+","+arr[3]);
		System.out.println(bytes2Int(arr));
		
		/*
		 * 字符串转换为字节
		 * 
		 * 
		 * 
		 * 
		 * 
		 */
		String desc = "111";
		byte [] b = desc.getBytes();
		
		System.out.println(new String(b));
		
	}

	/**
	 * int转换为byte
	 * @param number
	 * @return
	 */
	public static byte[] int2Bytes(int number){
		byte arr[] = new byte[4];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (byte)((int)(number >> i*8) & 0xff);
		} 
		return arr;
	}
	
	/**
	 * byte转换为int
	 * @param arr
	 * @return
	 */
	public static int bytes2Int(byte arr[]){
		int result = -1;
		for (int i = 0; i < arr.length; i++) {
			result += (int)((arr[i] & 0xff) << i*8);
		}
		return result;
	}
	
}
