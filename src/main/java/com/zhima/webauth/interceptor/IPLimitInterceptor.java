package com.zhima.webauth.interceptor;

import com.zhima.webauth.util.IPWhiteUtil;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IPLimitInterceptor extends HandlerInterceptorAdapter {

    private WhiteIpService whiteIpService;

    public IPLimitInterceptor(WhiteIpService whiteIpService){
        this.whiteIpService=whiteIpService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String ip = getIpAddress(request);
        String whiteIPList = whiteIpService.getWhiteIpList();
        if(IPWhiteUtil.checkLoginIP(ip, whiteIPList)) {
            return super.preHandle(request, response, handler);
        }
        throw new Exception("IP非法访问!");
    }

    private String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割    
        if(ip!=null && ip.length()>15){ //"***.***.***.***".length() = 15    
            if(ip.indexOf(",")>0){    
                ip = ip.substring(0,ip.indexOf(","));    
            }    
        }    
        System.out.println("访问ip========="+ip);
        return ip;
    }
}