package pers.allen.explore.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

@SuppressWarnings("rawtypes")
public class SignatureUtils {
	
	public static String createRequestParamsStr(SortedMap<String, String> parameters) {
		StringBuffer sb = new StringBuffer();
		// 所有参与传参的参数按照ASCII排序（升序）
		Set<?> es = parameters.entrySet();
		Iterator<?> it = es.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = String.valueOf(entry.getKey());
			Object v = String.valueOf(entry.getValue());
			if (null != v && !"".equals(v) && !"sign".equals(k) && !"key".equals(k)) {
				sb.append(k + "=" + v + "&");
			}
		}
		String paramStr = sb.toString();
		return paramStr;
	}

	public static String createSign(String paramsStr, String appKey, String secretKey) {
		String appKeyStr = "appKey=".concat(appKey).concat("&");
		String params = appKeyStr.concat(paramsStr).concat("&key=").concat(secretKey);
		String sign = SHA1(params).toUpperCase();
		return sign;
	}

	public static String SHA1(String str) {
		try {
			MessageDigest digest = java.security.MessageDigest.getInstance("SHA-1");
			digest.update(str.getBytes());
			byte messageDigest[] = digest.digest();
			// Create Hex String
			StringBuffer hexStr = new StringBuffer();
			// 字节数组转换为 十六进制 数
			for (int i = 0; i < messageDigest.length; i++) {
				String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
				if (shaHex.length() < 2) {
					hexStr.append(0);
				}
				hexStr.append(shaHex);
			}
			return hexStr.toString();

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String getMD5(String source) {
		String s = null;
		char hexDigits[] = { // 用来将字节转换成 16 进制表示的字符串
		'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd',
				'e', 'f' };
		try {
			java.security.MessageDigest md = java.security.MessageDigest
					.getInstance("MD5");

			md.update(source.getBytes("UTF-8"));
			byte tmp[] = md.digest(); 
			char str[] = new char[16 * 2]; // 每个字节�? 16 进制表示的话，使用两个字符，
			int k = 0; // 表示转换结果中对应的字符位置
			for (int i = 0; i < 16; i++) { 
				byte byte0 = tmp[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			s = new String(str);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return s;
	}

}
