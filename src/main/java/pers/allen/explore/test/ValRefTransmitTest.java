package pers.allen.explore.test;

import org.junit.Test;

/**
 * 值传递和引用类型传递
 * @author lengyul
 * 基本数据类型是值传递,引用数据类型是引用传递（地址传递）
 * 无论是值传递还是引用类型传递的参数都是实参copy的一个副本,而不是实参本身。
 */
public class ValRefTransmitTest {
	
	@Test
	public void test(){
		
		int number  =  1; 
		change(number);  //传递的副本内容修改,不会改变原件本身.
		System.out.println("number:"+number);
		
		String str = "1";//their values cannot be changed after they are created. 
		//String的值每一次更改,等同于 重新创建一个对象,并将新的地址值赋值给str.
		change(str); //副本 val = 2 所指向的地址值已经改变,并不能改变str的值
		System.out.println("str:"+str);
		
		//可以使用  StringBuilder或StringBuffer 操作改变String
		StringBuilder sb =new StringBuilder("1");
		change(sb);
		System.out.println("sb:"+sb.toString());
		
		Person  p = new Person(15,"xxx");
		change(p);
		System.out.println(p.toString());
	}

	void change(int val){
		val = 2;
	}
	
	void change(String val){
		val = "2";
	}
	
	void change(Person person){
		Person  p = new Person(20,"lengyul");
		person  = p;  //String类型类似情况
		/*person.age = 20;
		person.name = "lengyul";*/
	}
	
	void change(StringBuilder sb){
		
		sb.append("2");
		
	} 
	
	public class Person{
		
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
