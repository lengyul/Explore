package pers.allen.explore.feature.java8.practice;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;

/**
 * Stream对集合的加强处理
 */
public class CollectionStream {

	private static List<Person> list = null;
	
	@Before
	public void before() {
		list = Arrays.asList(
			new Person(1, 1991, "p1",1),
			new Person(4, 1994, "p2",1),
			new Person(20, 2000, "p3",2),
			new Person(99, 1999, "p4",2),
			new Person(56, 1956, "p5",2)
		);
		Objects.requireNonNull(list);
	}
	
	@Test
	public void test() {
		System.out.println(getPersons(new Person(20, 2000, "p3",2)));
		System.out.println(getUpperCaseNames());
		System.out.println(getPersonsForAfter1995());
		System.out.println(groupByLevel());
	}

	// test
	@SuppressWarnings("unused")
	private static List<Person> deepCopy() {
		return Collections.unmodifiableList(list);
	}
	
	/**
	 * name 大写
	 * @return
	 */
	private static List<Person> getUpperCaseNames() {
		return list.stream().map((e) -> {
			e.setName(e.getName().toUpperCase());
			return e;
		}).collect(Collectors.toList());
	}
	
	/**
	 * 条件查找
	 * @return
	 */
	private static List<Person> getPersons(Person condition) {
		return list.stream().filter((e) -> {
			return isValid(e,condition);
		}).collect(Collectors.toList());			
	}
	
	private static boolean isValid(Person p,Person c) {
		if (p == c)
			return true;
		return p.id == c.id && p.year == c.year && p.level == c.level && p.name.equals(c.name); 
	}
	
	/**
	 * level 分组
	 * @return
	 */
	private static Map<Integer, List<Person>> groupByLevel() {
		return list.stream().collect(
				Collectors.groupingBy(Person::getLevel));
		
	}
	
	/**
	 * 获取 year > 1995 的人员姓名并排序
	 * @return
	 */
	private static List<String> getPersonsForAfter1995() {
		return list.stream()
				.filter(e -> e.getYear() > 1995) // 条件： year > 1995
				.sorted(Comparator.comparing(Person::getYear)) // 排序： 对 year 排序
				.map(Person::getName) // 返回结果：方法引用（不进行计算），返回 name 属性
				.collect(Collectors.toList()); // 组装结果： 收集所有的 name 属性为集合
	}

	private class Person {
		private int id;
		private int year;
		private String name;
		private int level;

		public Person(int id, int year, String name, int level) {
			this.id = id;
			this.year = year;
			this.name = name;
			this.level = level;
		}

		public int getId() {
			return id;
		}

		public int getYear() {
			return year;
		}

		public String getName() {
			return name;
		}

		public int getLevel() {
			return level;
		}

		public void setName(String name) {
			this.name = name;
		}

		@Override
		public String toString() {
			return "Person [id=" + id + ", year=" + year + ", name=" + name + ", level=" + level + "]";
		}
	}
	
}
