package pers.allen.explore.code;

import static org.junit.Assert.fail;
import org.junit.Test;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import pers.allen.explore.code.codec.UserInfo;
import pers.allen.explore.utils.HttpClientUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;



public class CodeTest {

	
	public synchronized static void printSync() {	
		System.out.println(Thread.currentThread().getName());
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws Exception {
	
		Integer i = 1;
		Integer i2 = new Integer(1);
		
		Thread t1 = new Thread(() -> printSync());
		Thread t2 = new Thread(() -> printSync());

		t1.start();
		t2.start();
		TimeUnit.SECONDS.sleep(10);
		
		System.out.println(t2.getState());
		
		/*List<UserInfo> list = new ArrayList<UserInfo>();

		for (int i = 0; i < 100000; i++) {
			list.add(new UserInfo("user" + i, i));
		}
		LocalDateTime start = LocalDateTime.now();
		
		JSONArray jsonArray = new JSONArray();
		for (UserInfo userInfo : list) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("userID", userInfo.getUserID());
			jsonObject.put("username", userInfo.getUsername());
			jsonArray.add(jsonObject);
		}
		
		String json = JSONObject.toJSONString(list);
		LocalDateTime end = LocalDateTime.now();
		System.out.println("耗时：" + Duration.between(start, end).getNano());*/
//		System.out.println("json："+ json);
		
		
		// 分组
		/*
		 * list.add(new UserInfo("lili", 1)); list.add(new UserInfo("lengyul",
		 * 2)); list.add(new UserInfo("aaa", 1)); list.add(new UserInfo("bbb",
		 * 2)); list.add(new UserInfo("ccc", 3));
		 */
		/*
		 * Map<Integer, List<UserInfo>> dataMap =
		 * list.stream().collect(Collectors.groupingBy(UserInfo::getUserID));
		 * System.out.println(dataMap);
		 */
		
		
		/*JSONArray  detailArr = new JSONArray(); // json 数组
		JSONObject detailJson = new JSONObject();
		// 数据列表
		List<String> dataList = new ArrayList<>();
		dataList.add("1");dataList.add("2");
		dataList.add("3");dataList.add("4");
		dataList.add("5");
		// 保存 detailJson 到 detailArr
		for (String str : dataList) {
			detailJson.put("str", str);
			detailArr.add(detailJson);
		}
		System.out.println(detailArr.toString());*/
		
		
		/*Map<String, String> map = new HashMap<>();
		map.put("a", null);
		// 如果 key 不存在 指定 default value
		System.out.println(map.getOrDefault("a", "123"));
		// 如果 key 存在，则什么都不做
		map.putIfAbsent("b", "1");
		// 如果 key 存在，则什么都不做
		map.computeIfAbsent("b", k -> "val_"+ k);
		// 修改 value 
		map.computeIfPresent("a", (k,v) -> k + v);
		System.out.println(map);*/
		
		
		/*String json = "{}";
		String result = HttpClientUtils.httpPost("http://copdtest.appsbu.com:8000/api/device/daya-resperitor-reciver", json);
		System.out.println("接收到返回结果："+ result);*/
		
		
		
		
		
		/*long l=0xfffL; 	
		
		long now = LocalDate.now().plusDays(1L).atTime(5,0,0)
		.toInstant(ZoneOffset.UTC).getEpochSecond();		
		System.out.println(now);
		
		System.out.println(Instant.now().toEpochMilli());
		System.out.println(System.currentTimeMillis());
		
		System.out.println(Integer.toBinaryString(89>>1));
		String str = "123\n";
		
		System.out.println(str.trim());*/
		/*List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8);
		int sum = list.stream().reduce(0,(a,b) -> a+b);
		System.out.println(sum);
		
		SortedMap<String, String> map = Collections.synchronizedSortedMap(new TreeMap<>());*/
		
		/*int number = 3; // 0011
		System.out.println(Integer.toBinaryString(number << 2));
		System.out.println(Integer.toBinaryString(number >> 2));
		System.out.println(Integer.toBinaryString(number >>> 2));*/
		
		 /*byte[] bytes = {(byte) 0xe7,(byte) 0x83,(byte) 0xad,(byte) 0xe7,(byte) 0x82,(byte) 0xb9};
		 System.out.println(new String(bytes,"UTF-8"));*/
	}		

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
