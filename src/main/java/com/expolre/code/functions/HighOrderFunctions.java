package com.expolre.code.functions;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

public class HighOrderFunctions {
	private static <T> Predicate<T> notEqual(T t){
		return (v) -> !Objects.equals(v, t);
	}
	
	public static void main(String[] args) {
		Arrays.asList(1,2,3)
			  .stream()
			  .filter(notEqual(1))
			  .forEach(System.out::println);
	}
}
