package com.bcwms.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix="bcw")
public class BcwProperties {

    private static String uploadFileFolder;

    private static String uploadFileUrl;

    private static Boolean debugMode;

    private static String blockchainUrl;

    private static String bucketName;

    private String corePoolSize;

    private String maxPoolSize;

    private String queueCapacity;

    private String threadNamePrefix;

    public static String getUploadFileFolder() {
        return uploadFileFolder;
    }

    public void setUploadFileFolder(String uploadFileFolder) {
        BcwProperties.uploadFileFolder = uploadFileFolder;
    }

    public static String getUploadFileUrl() {
        return uploadFileUrl;
    }

    public void setUploadFileUrl(String uploadFileUrl) {
        BcwProperties.uploadFileUrl = uploadFileUrl;
    }

    public static Boolean getDebugMode() {
        return debugMode;
    }

    public void setDebugMode(Boolean debugMode) {
        BcwProperties.debugMode = debugMode;
    }

    public static String getBlockchainUrl() {
        return blockchainUrl;
    }

    public void setBlockchainUrl(String blockchainUrl) {
        BcwProperties.blockchainUrl = blockchainUrl;
    }

    public static String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        BcwProperties.bucketName = bucketName;
    }

    public String getCorePoolSize() {
        return corePoolSize;
    }

    public void setCorePoolSize(String corePoolSize) {
        this.corePoolSize = corePoolSize;
    }

    public String getMaxPoolSize() {
        return maxPoolSize;
    }

    public void setMaxPoolSize(String maxPoolSize) {
        this.maxPoolSize = maxPoolSize;
    }

    public String getQueueCapacity() {
        return queueCapacity;
    }

    public void setQueueCapacity(String queueCapacity) {
        this.queueCapacity = queueCapacity;
    }

    public String getThreadNamePrefix() {
        return threadNamePrefix;
    }

    public void setThreadNamePrefix(String threadNamePrefix) {
        this.threadNamePrefix = threadNamePrefix;
    }
}
