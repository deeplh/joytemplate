package com.keepjoy.core.properties;


import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;


//@PropertySource("classpath:config/keepjoy.properties","classpath:keepjoy.properties")
@ConfigurationProperties(prefix="keepjoy")
public class KeepJoyProperties {
    private String swaggerBasePackage;

    private List<String> urlMappings;

    private static String requestCharacterEncoding="utf-8";


    //分页
    private static String paginationCurrentPageKey="currentPage";
    private static String paginationPageSizeKey="pageSize";



    //文件上传相关限制,单位MB,KB
    private String maxFileSize="10MB";//单个文件最大
    private String maxRequestSize="50MB";//上传数据总大小

    private static Boolean dataDictMode=false;


    public static String getRequestCharacterEncoding() {
        return requestCharacterEncoding;
    }

    public void setRequestCharacterEncoding(String requestCharacterEncoding) {
        KeepJoyProperties.requestCharacterEncoding = requestCharacterEncoding;
    }


    public static String getPaginationCurrentPageKey() {
        return paginationCurrentPageKey;
    }

    public void setPaginationCurrentPageKey(String paginationCurrentPageKey) {
        KeepJoyProperties.paginationCurrentPageKey = paginationCurrentPageKey;
    }

    public static String getPaginationPageSizeKey() {
        return paginationPageSizeKey;
    }

    public void setPaginationPageSizeKey(String paginationPageSizeKey) {
        KeepJoyProperties.paginationPageSizeKey = paginationPageSizeKey;
    }

    public String getMaxFileSize() {
        return maxFileSize;
    }

    public  void setMaxFileSize(String maxFileSize) {
        this.maxFileSize = maxFileSize;
    }

    public  String getMaxRequestSize() {
        return maxRequestSize;
    }

    public  void setMaxRequestSize(String maxRequestSize) {
        this.maxRequestSize = maxRequestSize;
    }

    public static Boolean getDataDictMode() {
        return dataDictMode;
    }

    public void setDataDictMode(Boolean dataDictMode) {
        KeepJoyProperties.dataDictMode = dataDictMode;
    }

    public List<String> getUrlMappings() {
        return urlMappings;
    }

    public void setUrlMappings(List<String> urlMappings) {
        this.urlMappings = urlMappings;
    }

    public String getSwaggerBasePackage() {
        return this.swaggerBasePackage;
    }

    public void setSwaggerBasePackage(String swaggerBasePackage) {
        this.swaggerBasePackage = swaggerBasePackage;
    }
}
