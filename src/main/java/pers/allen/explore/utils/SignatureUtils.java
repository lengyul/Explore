package pers.allen.explore.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import com.alibaba.fastjson.JSONObject;

@SuppressWarnings("rawtypes")
public class SignatureUtils {
	
	/**
	 * Map转换为TreeMap（key有序）
	 * @param dataMap
	 * @return
	 */
	public static SortedMap<String, String> sortMap(Map<String, String> dataMap) {
		if (dataMap instanceof SortedMap) {
			return (SortedMap<String, String>) dataMap;
		}	
		return new TreeMap<>(dataMap);	
	}
	
	/**
	 * JSONObject转换为TreeMap
	 * @param jsonObject
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static SortedMap<String, String> toSortMap(JSONObject jsonObject) {
		return sortMap((Map) jsonObject);
	}
	
	/**
	 * Map中的key、value拼接为字符串（用于生成签名）
	 * @param params
	 * @return
	 */
	public static String spliceParams(SortedMap<String, String> params) {
		if (params == null)
			return null;
		StringBuilder sb = new StringBuilder();
		params.entrySet().stream().filter(e -> {
			return e.getValue() !=null && e.getValue().length() > 0;
		}).forEach(e -> {
			sb.append(e.getKey()).append("=").append(e.getValue()).append("&");
		});
		if(sb.length()>0){
			sb.deleteCharAt(sb.length()-1);
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
