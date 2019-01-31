package pers.allen.explore.utils;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Random;

public class RandomUtils {
	
	public static void main(String[] args) {
		// System.out.println(randomNumeric(5));
		//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime ldt = LocalDateTime.now();
		
		
		
		for (int i = 0; i < 20; i++) {			
			new Thread(() -> {
				//System.out.println(randomNumeric(5));
				try {
				//	System.out.println(sdf.parse("2018-12-07 06:02:20"));
					
					System.out.println(ldt.format(formatter));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}).start();
		}
		
	}
	
	static Random random = new Random();
	
	public static synchronized String randomNumeric(int count){
		StringBuilder sb = new StringBuilder();
		while (count > 0) {
			int number = random.nextInt(10);
			sb.append(number);
			count--;
		}
		return sb.toString();
	}
	
	
}
