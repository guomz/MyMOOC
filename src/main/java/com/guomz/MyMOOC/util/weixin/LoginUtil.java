package com.guomz.MyMOOC.util.weixin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.guomz.MyMOOC.dto.LoginResult;
import com.guomz.MyMOOC.dto.WechatInfo;
import com.guomz.MyMOOC.entity.UserInfo;

public class LoginUtil {

	private static String appID="wx5867d8c46bdad2df";
	private static String appsercet="0680b4eacea822ace42cf56d5a5b74ab";
	
	/**
	 * 向微信接口发送请求获取openid
	 * @param code
	 * @return
	 * @throws Exception
	 */
	public static LoginResult getLoginResult(String code) throws Exception
	{
		String url="https://api.weixin.qq.com/sns/jscode2session?appid="+appID+"&secret="+appsercet+"&js_code="+code+"&grant_type=authorization_code";
		String resultStr=httpsRequest(url, "GET", null);
		ObjectMapper objectMapper=new ObjectMapper();
		LoginResult loginResult=null;
		try {
			loginResult = objectMapper.readValue(resultStr, LoginResult.class);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return loginResult;
	}
	
	/**
	 * 发送https请求
	 * @param requestUrl
	 * @param requestMethod
	 * @param outputStr
	 * @return
	 */
	public static String httpsRequest(String requestUrl, String requestMethod, String outputStr) {
        StringBuffer buffer = new StringBuffer();
        try {
            // 创建SSLContext对象，并使用我们指定的信任管理器初始化
            TrustManager[] tm = { new MyX509TrustManager() };
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init(null, tm, new java.security.SecureRandom());
            // 从上述SSLContext对象中得到SSLSocketFactory对象
            SSLSocketFactory ssf = sslContext.getSocketFactory();

            URL url = new URL(requestUrl);
            HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
            httpUrlConn.setSSLSocketFactory(ssf);

            httpUrlConn.setDoOutput(true);
            httpUrlConn.setDoInput(true);
            httpUrlConn.setUseCaches(false);
            // 设置请求方式（GET/POST）
            httpUrlConn.setRequestMethod(requestMethod);

            if ("GET".equalsIgnoreCase(requestMethod))
                httpUrlConn.connect();

            // 当有数据需要提交时
            if (null != outputStr) {
                OutputStream outputStream = httpUrlConn.getOutputStream();
                // 注意编码格式，防止中文乱码
                outputStream.write(outputStr.getBytes("UTF-8"));
                outputStream.close();
            }

            // 将返回的输入流转换成字符串
            InputStream inputStream = httpUrlConn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            bufferedReader.close();
            inputStreamReader.close();
            // 释放资源
            inputStream.close();
            inputStream = null;
            httpUrlConn.disconnect();
        } catch (ConnectException ce) {
        	System.out.println(ce.getMessage());
        } catch (Exception e) {
        	System.out.println(e.getMessage());
        }
        return buffer.toString();
    }
	
	public static UserInfo generateUserInfo(WechatInfo wechatInfo,String openid)
	{
		UserInfo userInfo=new UserInfo();
		userInfo.setNickName(wechatInfo.getNickName());
		userInfo.setAvatarUrl(wechatInfo.getAvatarUrl());
		userInfo.setOpenid(openid);
		return userInfo;
	}
}
