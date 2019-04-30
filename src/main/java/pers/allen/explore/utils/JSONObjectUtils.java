package pers.allen.explore.utils;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;

/**
 * JSONO对象的处理
 * 
 * @author lengyul
 *
 */
public class JSONObjectUtils {

	/**
	 * 将json数据转换为JSON对象(顺序不改变)
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static JSONObject parse(String jsonStr) {
		LinkedHashMap<String, Object> json = JSON.parseObject(jsonStr, LinkedHashMap.class, Feature.OrderedField);
		JSONObject jsonObject = new JSONObject(true);
		jsonObject.putAll(json);
		return jsonObject;
	}

	/**
	 * JSONObject（key）参数名ASCII码从小到大排序
	 * 
	 * @param json
	 * @return
	 */
	public static JSONObject toAscii(JSONObject jsonObject) {
		Map<String, Object> jsonMap = jsonObject;
		return new JSONObject(new TreeMap<>(jsonMap));
	}
	
}
