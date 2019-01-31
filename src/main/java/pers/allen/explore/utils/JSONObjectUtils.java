package pers.allen.explore.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

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
	 * 将string数据转换为JSON对象(顺序不改变)
	 * 
	 * @return
	 */
	public static JSONObject parseStrSort(String reqData) {
		LinkedHashMap<String, Object> json = JSON.parseObject(reqData, LinkedHashMap.class, Feature.OrderedField);
		JSONObject jsonObject = new JSONObject(true);
		jsonObject.putAll(json);
		return jsonObject;
	}

	/**
	 * JSONObject参数名 ASCII 码从小到大排序
	 * 
	 * @param json
	 * @return
	 */
	public static JSONObject getAsciiObjct(JSONObject json) {
		Map<String, Object> jsonMap = json;
		List<String> keys = new ArrayList<String>(jsonMap.keySet());
		Collections.sort(keys);
		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
		for (int i = 0; i < keys.size(); i++) {
			if (StringUtils.isNotEmpty(keys.get(i))) {
				map.put(keys.get(i), jsonMap.get(keys.get(i)));
			}
		}
		JSONObject jsonObject = new JSONObject(map);
		return jsonObject;
	}

}
