package com.keepjoy.weixin.properties;


import org.springframework.boot.context.properties.ConfigurationProperties;


//@PropertySource("classpath:config/keepjoy.properties","classpath:keepjoy.properties")
@ConfigurationProperties(prefix="keepjoy.weixin")
public class WeixinProperties {
    private static String appId;
    private static String appSecret;
    private static String associateUserClass;


    public static String getAppId() {
        return appId;
    }

    public  void setAppId(String appId) {
        WeixinProperties.appId = appId;
    }

    public static String getAppSecret() {
        return appSecret;
    }

    public  void setAppSecret(String appSecret) {
        WeixinProperties.appSecret = appSecret;
    }

    public static String getAssociateUserClass() {
        return associateUserClass;
    }

    public  void setAssociateUserClass(String associateUserClass) {
        WeixinProperties.associateUserClass = associateUserClass;
    }
}
