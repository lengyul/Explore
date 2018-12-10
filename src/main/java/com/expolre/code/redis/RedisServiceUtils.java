package com.expolre.code.redis;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

public class RedisServiceUtils implements RedisService{

	RedisMsg smsg = new RedisMsg(RedisCmd.SET);
	RedisMsg gmsg = new RedisMsg(RedisCmd.GET);
	
	@Override
	public Object getKey(String key) {
		if (StringUtils.isEmpty(key)) {
			throw new NullPointerException("key is not null");
		}
		gmsg.setKey(key);
		return RedisRespResult.get(gmsg);
	}

	@Override
	public boolean setKey(String key, Object value) {
		
		Map<String, Object> dataMap = new HashMap<>();
		dataMap.put(key, value);
		smsg.setDataMap(dataMap);
		return RedisRespResult.set(smsg);
	}

	@Override
	public boolean setKey(Map<String, Object> dataMap) {
		smsg.setDataMap(dataMap);
		return RedisRespResult.set(smsg);
	}
	
	
}
