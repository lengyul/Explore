package com.expolre.code.redis;
import java.util.Map;

public class RedisServiceImpl implements RedisService{
	
	RedisService redisService = new RedisServiceUtils();
	
	@Override
	public Object getKey(String key) {
		
		return redisService.getKey(key);
	}

	@Override
	public boolean setKey(String key, Object value) {
		
		return redisService.setKey(key, value);
	}

	@Override
	public boolean setKey(Map<String, Object> dataMap) {
		
		return redisService.setKey(dataMap);
	}

}
