package com.zhima.wxauth.api;


/**
 * 小程序配置工具
 *
 */
public class WxaConfigKit {
	private static WxaConfig wxaConfig=new WxaConfig();

    // 开发模式将输出消息交互 xml、json 到控制台
    private static boolean devMode = false;

    public static void setDevMode(boolean devMode) {
        WxaConfigKit.devMode = devMode;
    }

    public static boolean isDevMode() {
        return devMode;
    }

    public static void setWxaConfig(WxaConfig wxaConfig) {
        WxaConfigKit.wxaConfig = wxaConfig;
    }
    
    public static WxaConfig getWxaConfig() {
        return WxaConfigKit.wxaConfig;
    }
}
