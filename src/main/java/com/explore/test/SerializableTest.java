package com.explore.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Arrays;

import org.junit.Test;


/**
 * 序列化与反序列化
 * @author lengyul
 *
 */
public class SerializableTest implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9073022170396070571L;

	/**
	 * 对象写入二进制文本 序列化
	 * @throws IOException
	 */
	@Test
	public void test() throws IOException{
		Person p1 =new Person(20, "lengyul");
		Person p2 =new Person(15, "lengyul");
		
		OutputStream op =new FileOutputStream("Person");
		ObjectOutput oop =new ObjectOutputStream(op);
		oop.writeObject(Arrays.asList(p1,p2));
		oop.close();
		
	}
	/**
	 * 二进制内容转换为对象 反序列化
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	@Test
	public void detest() throws IOException, ClassNotFoundException{
		InputStream in =new FileInputStream("Person");
		ObjectInput oi =new ObjectInputStream(in);
		Object obj = oi.readObject();
		System.err.println(obj);
		oi.close();
	}
	
	
	@Test
	public void testFile() throws IOException{
		File file =new File("0607");
		InputStream is =new FileInputStream(file);
		
		byte [] b =new byte[1024];
		while (is.read() != -1) {
			is.read(b);
		}
		is.close();
		String str =new String(b);
		System.out.println(str);
	}
	
	
	public class Person implements Serializable{
		/**
		 * 
		 */
		private static final long serialVersionUID = -7628572132339205497L;
		public Person(int age, String name) {
			this.age = age;
			this.name = name;
		}
		int    age;
		String name;
		@Override
		public String toString() {
			return "Person [age=" + age + ", name=" + name + "]";
		}
	}

}
