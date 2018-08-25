/**
 * Copyright (c) 2011-2014, L.cm 卢春梦 (qq596392912@gmail.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package com.zhima.wxauth.api;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

/**
 * 小程序支付接口
 *
 */
@Component
public class WxaPayApi {
	
	private final static Logger logger = LoggerFactory.getLogger(WxaPayApi.class);

	private WxaConfig wxaConfig=new WxaConfig();

	/**
     * 小程序统一下单
     * @param order 小程序支付订单信息封装
     * @throws PaymentException 支付异常
     * @return Map
     */
    public Map<String, String> unifiedOrder(WxaOrder order) throws PaymentException {
        Map<String, String> params = new HashMap<String, String>();
        params.put("appid", order.getMchId());
        params.put("mch_id", order.getMchId());
        params.put("body", order.getBody());
        params.put("out_trade_no", order.getOutTradeNo());
        params.put("total_fee", order.getTotalFee());
        params.put("spbill_create_ip",order.getSpbillCreateIp());
        params.put("trade_type", PaymentApi.TradeType.JSAPI.name());
        params.put("nonce_str", System.currentTimeMillis() / 1000 + "");
        params.put("notify_url", order.getNotifyUrl());
        params.put("openid", order.getOpenId());

//         附加数据，在查询API和支付通知中原样返回，该字段主要用于商户携带订单的自定义数据
        String attach = order.getAttach();
        if (StringUtils.isNotEmpty(attach)) {
            params.put("attach", attach);
        }
        // 订单生成时间，格式为yyyyMMddHHmmss
        String timeStart = order.getTimeStart();
        if (StringUtils.isNotEmpty(timeStart)) {
            params.put("time_start", timeStart);
        }
        // 订单失效时间，格式为yyyyMMddHHmmss。注意：最短失效时间间隔必须大于5分钟
        String timeExpire = order.getTimeExpire();
        if (StringUtils.isNotEmpty(timeExpire)) {
            params.put("time_expire", timeExpire);
        }
//         商品标记，代金券或立减优惠功能的参数，
        String goodsTag = order.getGoodsTag();
        if (StringUtils.isNotEmpty(goodsTag)) {
            params.put("goods_tag", goodsTag);
        }
        //String signKey = wxaConfig.getPaternerKey();
        String signKey = order.getSignKey();

        // 签名
        String sign = PaymentKit.createSign(params, signKey);
        params.put("sign", sign);
        // 统一下单
        String xmlResult = PaymentApi.pushOrder(params);
        Map<String, String> result = PaymentKit.xmlToMap(xmlResult);

        String return_code = result.get("return_code");
        String return_msg = result.get("return_msg");
        if (StringUtils.isEmpty(return_code) || !"SUCCESS".equals(return_code)) {
            throw new PaymentException(return_code, return_msg);
        }
        String result_code = result.get("result_code");
        if (StringUtils.isEmpty(result_code) || !"SUCCESS".equals(result_code)) {
        	String err_code_des=result.get("err_code_des");
            throw new PaymentException(result_code,err_code_des);
        }
        // 以下字段在return_code 和result_code都为SUCCESS的时候有返回
        String prepay_id = result.get("prepay_id");
        
        // 小程序调起支付数据签名字段信息
        Map<String, String> packageParams = new HashMap<String, String>();
        packageParams.put("appId", wxaConfig.getAppId());
        packageParams.put("timeStamp", System.currentTimeMillis() / 1000 + "");
        packageParams.put("nonceStr", System.currentTimeMillis() + "");
        packageParams.put("package", "prepay_id=" + prepay_id);
        packageParams.put("signType", "MD5");
        String packageSign = PaymentKit.createSign(packageParams, signKey);
        packageParams.put("paySign", packageSign);
        return packageParams;
    }

}
