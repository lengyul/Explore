package com.explore.code.redis;

import java.util.Map;

public interface RedisService {

	Object getKey(String key);
	
	boolean setKey(String key,Object value);
	
	boolean setKey(Map<String,Object> dataMap);
	
}
