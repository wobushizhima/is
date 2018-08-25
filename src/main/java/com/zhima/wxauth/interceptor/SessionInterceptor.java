package com.zhima.wxauth.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhima.dto.JsonResult;
import com.zhima.kit.RedisKit;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class SessionInterceptor implements HandlerInterceptor {
	@Autowired
	private RedisKit redisKit;
	private String AUTH="$2a$10$gZ40APgz.KmGqHjjjibUV.m5VXXQyJ1k89CGVyWhO5iCyidmg7u1e";

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2) throws Exception {
		String sessionid = arg0.getHeader("sessionid");
		Object obj=redisKit.get(sessionid);
		if(StringUtils.isEmpty(sessionid)) {
			JsonResult result=new JsonResult();
			result.setSuccess(false);
			result.setErrorCode(21001);
			result.setMsg("未登陆");
			arg1.setContentType("application/json;charset=UTF-8");
			arg1.getWriter().write(JSON.toJSONString(result));
			return false;
		}
		if(obj==null||StringUtils.isEmpty(obj.toString())) {
			JsonResult result=new JsonResult();
			result.setSuccess(false);
			result.setErrorCode(21002);
			result.setMsg("您的登录信息已失效，请重新登录");
			arg1.setContentType("application/json;charset=UTF-8");
			arg1.getWriter().write(JSON.toJSONString(result));
			return false;
		}
		JSONObject loginUser = JSON.parseObject(obj.toString());
		arg0.setAttribute("loginUser", loginUser);
		return true;
		
	}
	
	
}