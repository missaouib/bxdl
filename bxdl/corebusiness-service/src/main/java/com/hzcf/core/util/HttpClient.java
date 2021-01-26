package com.hzcf.core.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.security.MessageDigest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class HttpClient {

	    /**
	     * 构建签名
	     * 
	     * @param paramsMap
	     *            参数
	     * @param secret
	     *            密钥
	     * @return
	     * @throws IOException
	     */
	    public static String buildSign(Map<String, ?> paramsMap, String secret) throws IOException {
	        Set<String> keySet = paramsMap.keySet();
	        List<String> paramNames = new ArrayList<String>(keySet);

	        Collections.sort(paramNames);
            System.out.println(paramNames);
	        StringBuilder paramNameValue = new StringBuilder();

	        for (String paramName : paramNames) {
	            paramNameValue.append(paramName).append(paramsMap.get(paramName));
	        }

	        String source = secret + paramNameValue.toString() + secret;

	        return md5(source);
	    }

	    /**
	     * 生成md5,全部大写
	     * 
	     * @param message
	     * @return
	     */
	    public static String md5(String message) {
	        try {
	            // 1 创建一个提供信息摘要算法的对象，初始化为md5算法对象
	            MessageDigest md = MessageDigest.getInstance("MD5");

	            // 2 将消息变成byte数组
	            byte[] input = message.getBytes();

	            // 3 计算后获得字节数组,这就是那128位了
	            byte[] buff = md.digest(input);

	            // 4 把数组每一字节（一个字节占八位）换成16进制连成md5字符串
	            return byte2hex(buff);
	        } catch (Exception e) {
	            throw new RuntimeException(e);
	        }
	    }

	    /**
	     * 二进制转十六进制字符串
	     * 
	     * @param bytes
	     * @return
	     */
	    private static String byte2hex(byte[] bytes) {
	        StringBuilder sign = new StringBuilder();
	        for (int i = 0; i < bytes.length; i++) {
	            String hex = Integer.toHexString(bytes[i] & 0xFF);
	            if (hex.length() == 1) {
	                sign.append("0");
	            }
	            sign.append(hex.toUpperCase());
	        }
	        return sign.toString();
	    }

	    public static String getTime() {
	        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	    }
	    
	    /**
	     * http请求
	     * @param remoteUrl
	     * @return
	     */
	    public static String post(String remoteUrl, String content) throws ParseException {
	        CloseableHttpClient httpclient = HttpClients.createDefault();
	        CloseableHttpResponse response = null;
	        String result = null;
	        try {
	            HttpPost httpPost = new HttpPost(remoteUrl);
	            StringEntity entity = new StringEntity(content, "UTF-8");
	            httpPost.setEntity(entity);
	            response = httpclient.execute(httpPost);

	            int statusCode = response.getStatusLine().getStatusCode();
	            if (statusCode == HttpStatus.SC_OK) {
	                HttpEntity resEntity = response.getEntity();
	                result = EntityUtils.toString(resEntity);
//	                System.out.println("下好好的、、"+result);
	                // 消耗掉response
	                EntityUtils.consume(resEntity);
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally {
	            HttpClientUtils.closeQuietly(httpclient);
	            HttpClientUtils.closeQuietly(response);
	        }
	        return result;
	    }
}
