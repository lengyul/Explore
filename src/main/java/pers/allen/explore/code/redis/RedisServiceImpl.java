package pers.allen.explore.code.redis;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

public class RedisServiceImpl implements RedisService{
	
	
	@Override
	public Object getKey(String key) {
		if (StringUtils.isEmpty(key)) {
			throw new NullPointerException("key is not null");
		}
		return RedisRespResult.get(RedisServiceUtils.buildGetKey(key));
	}

	@Override
	public boolean setKey(String key, Object value) {
		
		return RedisRespResult.set(RedisServiceUtils.buildSet(key, value));
	}

	@Override
	public boolean setKey(Map<String, Object> dataMap) {
		
		return RedisRespResult.set(RedisServiceUtils.buildSetMap(dataMap));
	}

}
