package com.zhima.wxauth.controller;

import com.zhima.dto.JsonResult;
import com.zhima.kit.RedisKit;
import com.zhima.util.SessionUtil;
import com.zhima.wxauth.api.ApiResult;
import com.zhima.wxauth.api.WxaUserApi;
import com.zhima.wxauth.constant.RedisConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by superz on 2018/8/9.
 */
@RestController
public class WxLoginController {
    @Autowired
    private WxaUserApi wxaUserApi;

    @Autowired
    private RedisKit redisKit;

    @PostMapping(value="/user/wxLogin")
    public JsonResult<Map<String,String>> wxLogin(@RequestParam String jsCode){
        JsonResult<Map<String,String>> result=new JsonResult<Map<String,String>>();
        ApiResult apiResult=wxaUserApi.getSessionKey(jsCode);
        if(apiResult==null||apiResult.getAttrs()==null||apiResult.getAttrs().get("session_key")==null) {
            result.setSuccess(false);
            result.setErrorCode(21001);
            result.setMsg("登录不成功，获取session_key失败");
            return result;

        }
        Map<String,String> data=new HashMap<String,String>();
        String openid=apiResult.getAttrs().get("openid").toString();
        String session_key=apiResult.getAttrs().get("session_key").toString();
        String unionid=null;
        if(apiResult.getAttrs().get("unionid")!=null) {
            unionid=apiResult.getAttrs().get("unionid").toString();
        }
//        Custom custom=customService.getCustomByOpenid(openid);
//        if(custom==null) {
//            custom=new Custom();
//            //新用户
//            custom.setOpenid(openid);
//            custom.setUnionid(unionid);
//            custom.setUserType(1);
//            Long userId=customService.saveUser(custom);
//            custom.setUserId(userId);
//        }
        //TODO 从判断是否是新的用户
        String sessionid= SessionUtil.generateSessionId();
        data.put("sessionid", sessionid);
        //redisKit.set(sessionid, JSON.toJSONString(custom));
        redisKit.set(sessionid, session_key);
        redisKit.expire(sessionid, RedisConstant.REDIS_EXPIRE);
        result.setResult(data);
        System.out.println("login成功");
        return result;
    }

    @PostMapping(value="/user/wxLogout")
    public JsonResult wxLogout(@RequestParam String sessionid) {
        JsonResult jsonResult=new JsonResult();
        redisKit.del(sessionid);
        return jsonResult;
    }
}
