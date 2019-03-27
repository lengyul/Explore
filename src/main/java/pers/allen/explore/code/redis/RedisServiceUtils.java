package pers.allen.explore.code.redis;

import java.util.HashMap;
import java.util.Map;

public class RedisServiceUtils extends RedisServiceImpl{
	
	/*RedisMsg smsg = new RedisMsg(RedisCmd.SET);
	RedisMsg gmsg = new RedisMsg(RedisCmd.GET);*/
	private static final RedisService redisService = new RedisServiceImpl();
	public static RedisService getInstance() {
		
		return redisService;
	}
	
	public static RedisMsg buildGetKey(String key) {
		
		return new RedisMsg(RedisCmd.GET, key);
	}
	
	public static RedisMsg buildSet(String key, Object value) {
		Map<String, Object> dataMap = new HashMap<>();
		dataMap.put(key, value);
		return buildSetMap(dataMap);
	}
	
	public static RedisMsg buildSetMap(Map<String, Object> dataMap) {
		
		return new RedisMsg(RedisCmd.SET, dataMap);
	}
	
}
