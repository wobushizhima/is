package com.zhima.wxauth.api;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

import com.zhima.kit.RedisKit;
import com.zhima.wxauth.util.RetryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

@Component
public class AccessTokenApi {
	
	private static WxaConfig wxaConfig=new WxaConfig();
	
	// "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	private static String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential";
	
	@Autowired
	private RedisKit redisKit;
	
	/**
	 * 从缓存中获取 access token，如果未取到或者 access token 不可用则先更新再获取
	 */
	public  AccessToken getAccessToken() {
		String appId = wxaConfig.getAppId();
		Object result=redisKit.get(appId);
		AccessToken token=null;
		if(result!=null) {
			token = JSON.parseObject(result+"",AccessToken.class);
		}
		
		if (token != null && token.isAvailable())
			return token;
		
		refreshAccessToken();
		return JSON.parseObject(redisKit.get(appId)+"",AccessToken.class);
	}
	
	/**
	 * 直接获取 accessToken 字符串，方便使用
	 * @return String accessToken
	 */
	public  String getAccessTokenStr() {
		return getAccessToken().getAccessToken();
	}
	
	/**
	 * 强制更新 access token 值
	 */
	public synchronized void refreshAccessToken() {
		String appId = wxaConfig.getAppId();
		String appSecret = wxaConfig.getAppSecret();
		HashMap<String,String> paraMap=new HashMap<String,String>();
	    paraMap.put("appid", appId);
		paraMap.put("secret", appSecret);
		
		// 最多三次请求
		AccessToken result = RetryUtils.retryOnException(3, new Callable<AccessToken>() {
			
			@Override
			public AccessToken call() throws Exception {
				String json = HttpUtils.get(url, paraMap);
				return new AccessToken(json);
			}
		});
		
		// 三次请求如果仍然返回了不可用的 access token 仍然 put 进去，便于上层通过 AccessToken 中的属性判断底层的情况
		redisKit.set(appId, result);
	}

}