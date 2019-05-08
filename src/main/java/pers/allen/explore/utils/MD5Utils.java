package pers.allen.explore.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.SortedMap;


public class MD5Utils {
	
	/**
	 * md5
	 * @param b
	 * @return
	 */
	private static String md5(byte[] b) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.reset();
			md.update(b);
			byte[] hash = md.digest();
			StringBuffer outStrBuf = new StringBuffer(32);
			for (int i = 0; i < hash.length; i++) {
				int v = hash[i] & 0xFF;
				if (v < 16) {
					outStrBuf.append('0');
				}
				outStrBuf.append(Integer.toString(v, 16).toLowerCase());
			}
			return outStrBuf.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return new String(b);
		}
	}
	
	
	/**
	 * 签名
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public static String sign(SortedMap<String,String> params,String appkey) throws Exception{
		if(params.containsKey("sign"))// 签名不包含sign字段
			params.remove("sign");
		params.put("key", appkey);
		String str = SignatureUtils.spliceParams(params);
		String sign = md5(str.getBytes("UTF-8"));
		params.remove("key");
		return sign;
	}
	
	/**
	 * 验证签名
	 * @param param
	 * @param appkey
	 * @return
	 * @throws Exception
	 */
	public static boolean validSign(SortedMap<String,String> params,String appkey) throws Exception{
		 if(params != null){
			 if(!params.containsKey("sign"))
	    			return false;
			 String sign = params.get("sign").toString();
			 String mysign = sign(params, appkey);
			 return sign.toLowerCase().equals(mysign.toLowerCase());
		 }
		 return false;
	 }
	
	
	/*public static void main(String[] args) throws Exception {
		final String appkey = "abagqwe123213";
		SortedMap<String, String> params = new TreeMap<>();
		params.put("ac", "atomic");
		params.put("zxc", "zoom");
		params.put("bxc", "boom");
		String sign = sign(params, appkey);
		System.out.println(sign);
		params.put("sign", sign);
		System.out.println(validSign(params, appkey));
	}*/
	
}
