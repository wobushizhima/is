/**
 * Copyright (c) 2011-2014, L.cm 卢春梦 (qq596392912@gmail.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package com.zhima.wxauth.api;

import java.io.Serializable;

/**
 * 微信小程序配置
 *
 */
public class WxaConfig implements Serializable {
    private static final long serialVersionUID = 8274925821039698118L;
    
    private String appId = "wxce52e9422e908b7b";
    private String mchId="1499055412";
    private String appSecret = "8484da68695057fbc17b2da0c89ba1d9";
    private String paternerKey="1082B2E24B534B08365BEF8C47F85BD9";
    		//"JiangsuTangshanGuojiLvxingshe123";
    private String signType="MD5";
    private String token = null;
    private String encodingAesKey = null;
    private boolean messageEncrypt = false;    // 消息加密与否
    
    private String notify_url="https://www.sijitangshan.com/mpserver/pay/payNotify";
    
    public String getAppId() {
        return appId;
    }
    public void setAppId(String appId) {
        this.appId = appId;
    }
    public String getAppSecret() {
        return appSecret;
    }
    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public String getEncodingAesKey() {
        return encodingAesKey;
    }
    public void setEncodingAesKey(String encodingAesKey) {
        this.encodingAesKey = encodingAesKey;
    }
    public boolean isMessageEncrypt() {
        return messageEncrypt;
    }
    public void setMessageEncrypt(boolean messageEncrypt) {
        this.messageEncrypt = messageEncrypt;
    }
	public String getMchId() {
		return mchId;
	}
	public void setMchId(String mchId) {
		this.mchId = mchId;
	}
	public String getSignType() {
		return signType;
	}
	public void setSignType(String signType) {
		this.signType = signType;
	}
	public String getPaternerKey() {
		return paternerKey;
	}
	public void setPaternerKey(String paternerKey) {
		this.paternerKey = paternerKey;
	}
	public String getNotify_url() {
		return notify_url;
	}
	public void setNotify_url(String notify_url) {
		this.notify_url = notify_url;
	}
	
	
}
