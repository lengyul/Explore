package pers.allen.explore.code;

import static org.junit.Assert.fail;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

import pers.allen.explore.effective.core.ImmutableClass;

public class CodeTest {

	
	public static void main(String[] args) {
		
		DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		List<String> list = new ArrayList<>();
		list.add("2019-04-23 16:10:50");
		list.add("2019-04-25 16:10:50");
		list.add("2019-04-24 16:12:50");
		list.add("2019-04-22 16:11:50");
		System.out.println(list);
		
	//	list = list.stream().sorted().collect(Collectors.toList());
	//	list = list.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
		/*list = list.stream()
				.sorted((v1,v2) -> {
					LocalDateTime l1 = LocalDateTime.parse(v1, dtf2);
					LocalDateTime l2 = LocalDateTime.parse(v2, dtf2);
					if (l1.isBefore(l2)) {
						return -1;
					} else if(l1.isAfter(l2)) {
						return 1;
					} else {						
						return 0;
					}
				})
				.collect(Collectors.toList());*/
		list.forEach(System.out::println);
	}
	
	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
