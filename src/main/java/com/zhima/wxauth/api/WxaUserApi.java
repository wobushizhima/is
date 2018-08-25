/**
 * Copyright (c) 2011-2014, L.cm 卢春梦 (qq596392912@gmail.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package com.zhima.wxauth.api;


import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * 微信小程序用户api接口
 *
 */
@Component
public class WxaUserApi {
    private static String jsCode2sessionUrl = "https://api.weixin.qq.com/sns/jscode2session";
    private static String getUserInfo = "https://api.weixin.qq.com/cgi-bin/user/info";
	private static String getFollowers = "https://api.weixin.qq.com/cgi-bin/user/get";
	private static String batchGetUserInfo = "https://api.weixin.qq.com/cgi-bin/user/info/batchget?access_token=";
	
	@Autowired
	private  AccessTokenApi accessTokenApi;

    /**
     * 获取sessionKey
     * @param jsCode 登录时获取的 code
     * @return ApiResult
     */
    public ApiResult getSessionKey(String jsCode) {
        WxaConfig wc = WxaConfigKit.getWxaConfig();
        Map<String, String> params = new HashMap<String, String>();
        params.put("appid", wc.getAppId());
        params.put("secret", wc.getAppSecret());
        params.put("js_code", jsCode);
        params.put("grant_type", "authorization_code");
        String para = PaymentKit.packageSign(params, false);
        // 构造url
        String url = jsCode2sessionUrl + "?" + para;
        return new ApiResult(HttpUtils.get(url));
    }

    /**
     * 解密用户敏感数据
     * @param sessionKey 会话密钥
     * @param encryptedData 明文
     * @param ivStr 加密算法的初始向量
     * @return {ApiResult}
     */
    public ApiResult getUserInfo(String sessionKey, String encryptedData, String ivStr) {
        WxaBizDataCrypt dataCrypt = new WxaBizDataCrypt(sessionKey);
        String json = dataCrypt.decrypt(encryptedData, ivStr);
        return new ApiResult(json);
    }

    /**
     * 验证用户信息完整性
     * @param sessionKey 会话密钥
     * @param rawData 微信用户基本信息
     * @param signature 数据签名
     * @return {boolean}
     */
    public boolean checkUserInfo(String sessionKey, String rawData, String signature) {
        StringBuffer sb = new StringBuffer(rawData).append(sessionKey);
        String encryData = HashKit.sha1(sb.toString());
        return encryData.equals(signature);
    }
    
	
	/**
	 * 获取用户基本信息（包括UnionID机制）
	 * @param openId 普通用户的标识，对当前公众号唯一
	 * @return ApiResult
	 */
	public  ApiResult getUserInfo(String openId) {
		HashMap<String,String> paraMap=new HashMap<String,String>();
		paraMap.put("access_token", accessTokenApi.getAccessTokenStr());
		paraMap.put("openid",openId);
		paraMap.put("lang","zh_CN");
	    return new ApiResult(HttpUtils.get(getUserInfo, paraMap));
	}
}
