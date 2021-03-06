package pers.allen.explore.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;

/**
 * Http请求发送工具类
 * @author lengyul
 *
 */
public class HttpClientUtils {

	// 编码格式UTF-8
	private static final String CHARSET_UTF8 = "UTF-8";

	// 设置连接超时时间ms
	private static final int CONNECT_TIMEOUT = 6000;

	// 请求获取数据的超时时间(即响应时间)ms
	private static final int SOCKET_TIMEOUT = 6000;

	private HttpClientUtils() {}
	/**
	 * 发送POST请求(JSON对象参数)
	 * 
	 * @param url
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public static String httpPost(String url, JSONObject obj) throws Exception {
		Map<String, Object> params = null;
		if (null != obj) {
			params = new HashMap<>();
			for (Object key : obj.keySet()) {
				params.put(key + "", obj.get(key));
			}
		}
		return httpPost(url, params);
	}

	/**
	 * HttpPost请求(Map对象参数)
	 * 
	 * @param url
	 * @param params
	 * @return
	 * @throws IOException
	 */
	public static String httpPost(String url, Map<String, Object> params) {
		String result = null;
		// 设置连接超时时间(ms)
		RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(CONNECT_TIMEOUT)
				.setSocketTimeout(SOCKET_TIMEOUT).build();
		// 创建HttpClient
		CloseableHttpClient httpClinet = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		HttpPost httpPost = new HttpPost(url);
		httpPost.setConfig(requestConfig);
		try {
			if (null != params) {
				List<NameValuePair> nvps = new ArrayList<NameValuePair>();
				for (Object key : params.keySet()) {
					if (params.get(key) != null)
						nvps.add(new BasicNameValuePair(key + "", params.get(key).toString()));
				}
				UrlEncodedFormEntity entity = new UrlEncodedFormEntity(nvps, CHARSET_UTF8);
				httpPost.setEntity(entity);
			}
			response = httpClinet.execute(httpPost);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				result = EntityUtils.toString(entity, CHARSET_UTF8); //接收响应实体
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			release(httpClinet, response);
		}
		return result;
	}

	/**
	 * 发送JSON串数据(Postman-raw流数据)
	 * 
	 * @param url
	 * @param json
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 */
	public static String httpPost(String url, String json) {
		String result = null;

		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse response = null;

		try {
			HttpPost post = new HttpPost(url);
			post.setHeader("Content-type", "application/json");
			StringEntity data = new StringEntity(json, CHARSET_UTF8);// json传递
			post.setEntity(data);
			response = httpClient.execute(post);		
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				result = EntityUtils.toString(entity, CHARSET_UTF8); //接收响应实体
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			release(httpClient, response);
		}
		return result;
	}

	/**
	 * 发送 GET请求,(JSON对象参数)
	 * 
	 * @param url
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public static String httpGet(String url, JSONObject obj) throws Exception {
		Map<String, Object> params = null;
		if (obj != null) {
			params = new HashMap<>();
			for (Object key : obj.keySet()) {
				params.put(key + "", obj.get(key));
			}
		}
		return httpGet(url, params);
	}

	/**
	 * 发送 GET请求,(Map对象参数)
	 * 
	 * @param url
	 * @return
	 */
	public static String httpGet(String url, Map<String, Object> params) {
		return httpGet(url, null, params);
	}

	/**
	 * 发送 GET请求HTTP(Map-请求头参数和请求参数)
	 * 
	 * @param url
	 * @param params
	 * @return
	 * @throws IOException
	 */
	public static String httpGet(String url, Map<String, String> headers, Map<String, Object> params) {

		StringBuffer param = new StringBuffer();
		int i = 0;
		if (params != null) {
			for (String key : params.keySet()) {
				if (i == 0)
					param.append("?");
				else
					param.append("&");
				param.append(key).append("=").append(params.get(key));
				i++;
			}
		}
		url += param;
		String result = null;
		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		try {
			HttpGet httpGet = new HttpGet(url);
			// 设置header
			httpGet.setHeader("Content-type", "application/json");
			if (headers != null && headers.size() > 0) {
				for (Map.Entry<String, String> entry : headers.entrySet()) {
					httpGet.setHeader(entry.getKey(), entry.getValue());
				}
			}
			response = httpClient.execute(httpGet);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				result = EntityUtils.toString(entity, CHARSET_UTF8); //接收响应实体
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			release(httpClient, response);
		}
		return result;
	}

	/**
	 * 释放资源
	 * 
	 * @param httpClinet
	 * @param response
	 * @throws IOException
	 */
	private static void release(CloseableHttpClient httpClinet, CloseableHttpResponse response) {
		try {
			if (response != null) {
				response.close();
			}
			if (httpClinet != null) {
				httpClinet.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * https 请求 
	 * @return
	 * @throws KeyStoreException
	 * @throws NoSuchAlgorithmException
	 * @throws KeyManagementException
	 */
	private static CloseableHttpClient createHttpsClient() throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
        SSLContext sslcontext = SSLContexts.custom()
                .loadTrustMaterial(null, (chain, authType) -> true)
                .build();

        SSLConnectionSocketFactory sslSf = new SSLConnectionSocketFactory(sslcontext, null, null,
                new NoopHostnameVerifier());

        return HttpClients.custom().setSSLSocketFactory(sslSf).build();
    }
	
	/**
	 * 接收HTTP请求(Post)读取流数据
	 * 
	 * @param request
	 * @return
	 * @throws IOException
	 */
	public static String getRequestMessage(HttpServletRequest request) throws IOException {
		request.setCharacterEncoding(CHARSET_UTF8);// 只针对POST请求方式
		StringBuffer resultBuffer = new StringBuffer();
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream(), CHARSET_UTF8));
			String tempLine = null;
			while ((tempLine = reader.readLine()) != null) {
				resultBuffer.append(tempLine);
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultBuffer.toString();
	}

	/**
	 * 接收HTTP请求数据(转JSON对象)
	 * 
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static JSONObject getRepuestParamsToJSONObjct(HttpServletRequest request) throws UnsupportedEncodingException {
		JSONObject jsonObject = null;
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		Enumeration<String> keys = request.getParameterNames();
		while (keys.hasMoreElements()) {
			String key = keys.nextElement();
			String[] values = request.getParameterValues(key);
			if (values.length == 1) {
				parameterMap.put(key, values[0]);
			} else if (values.length > 1) {
				String str = null;
				for (int i = 0; i < values.length; i++) {
					str += values[i];
					if (i < values.length - 1) {
						str += ",";
					}
				}
				parameterMap.put(key, str);
			}
		}
		if (parameterMap != null && parameterMap.size() > 0) {
			jsonObject = new JSONObject(parameterMap);
		}
		return jsonObject;
	}

}
