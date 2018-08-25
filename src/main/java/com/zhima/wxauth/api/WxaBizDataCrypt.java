/**
 * Copyright (c) 2011-2014, L.cm 卢春梦 (qq596392912@gmail.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package com.zhima.wxauth.api;

import java.security.AlgorithmParameters;
import java.security.Key;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


/**
 * 微信小程序-加密数据解密算法
 *
 */
public class WxaBizDataCrypt {
    private final String sessionKey;
//    final Decoder decoder = Base64.getDecoder();
//    final Encoder encoder = Base64.getEncoder();

    public WxaBizDataCrypt(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    /**
     * AES解密
     * @param encryptedData 密文
     * @param ivStr iv
     * @return {String}
     */
    public String decrypt(String encryptedData, String ivStr) {
    	Decoder decoder = Base64.getDecoder();
        byte[] bizData = decoder.decode(encryptedData);
        byte[] keyByte = decoder.decode(sessionKey);
        byte[] ivByte  = decoder.decode(ivStr);
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            Key sKeySpec = new SecretKeySpec(keyByte, "AES");
            // 初始化
            AlgorithmParameters params = AlgorithmParameters.getInstance("AES");
            params.init(new IvParameterSpec(ivByte));
            cipher.init(Cipher.DECRYPT_MODE, sKeySpec, params);
            byte[] original = cipher.doFinal(bizData);
            // 去除补位字符
            byte[] result = decoder.decode(original);
            return new String(result, Charsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException("aes解密失败");
        }
    }

}
