package pers.allen.explore.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import com.alibaba.fastjson.JSONObject;

@SuppressWarnings("rawtypes")
public class SignatureUtils {
	
	
	/**
	 * 生成签名
	 * @param data
	 * @return
	 */
	// String data = data += appkey;
	public static String generateSign(String data) {
		// implement
		return null;
	}
	
	public static String generateSign(String data, String appkey) {
		// implement
		return null;
	}
	
	/**
	 * 检验签名
	 * @param data
	 * @param appkey
	 * @param sign
	 * @return
	 */
	public static boolean checkSign(String data, String appkey, String sign) {
		String sign0 = generateSign(data, appkey);
		return sign0.equals(sign);
	}
	
	/**
	 * Map转换为TreeMap（key有序）
	 * @param dataMap
	 * @return
	 */
	public static Map<String, String> sortMap(Map<String, String> dataMap) {
		if (dataMap instanceof SortedMap) {
			return dataMap;
		}	
		return new TreeMap<>(dataMap);	
	}
	
	/**
	 * JSONObject转换为TreeMap
	 * @param jsonObject
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, String> toSortMap(JSONObject jsonObject) {
		return sortMap((Map) jsonObject);
	}
	
	/**
	 * Map中的key、value拼接为字符串（用于生成签名）
	 * @param params
	 * @return
	 */
	public static String spliceParams(Map<String, String> params) {
		List<String> keys = new ArrayList<String>(params.keySet());
		Collections.sort(keys);
		StringBuilder sb = new StringBuilder();
		/*keys.stream().forEach((key) -> { // 没有计数变量
			String value = paramsLowerKey.get(key);
		});*/
		for (int i = 0; i < keys.size(); i++) {
			String key = keys.get(i);
			String value = params.get(key);
			if (i == keys.size() - 1) {// 拼接时，不包括最后一个&字符
				sb.append(key + "=" + value);
			} else {
				sb.append(key + "=" + value + "&");
			}
		}
		return sb.toString().toLowerCase();
	}
	
	/**
	 * Map中的所有key转换为小写
	 * @param params
	 * @return
	 */
	@SuppressWarnings("unused")
	private static Map<String, String> paramKeytoLowerCase(Map<String, String> params) {
		Map<String, String> paramsLowerKey = new HashMap<String, String>();
		List<String> keys = new ArrayList<String>(params.keySet());
		keys.forEach((key) -> {
			String value = params.get(key);
			paramsLowerKey.put(key.toLowerCase(), value);
		});
		return paramsLowerKey;
	}
	
	
}
