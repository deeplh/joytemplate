package com.bcwms.factory;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;

import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.region.Region;

public class CosClientFactory {

    private static COSClient cosClient;


    private CosClientFactory() {

    }

    public static void init() {
        if (null == cosClient) {
            // 1 初始化用户身份信息(secretId, secretKey)
            COSCredentials cred = new BasicCOSCredentials("AKID1wUvAkXSYSCk6GshEgy4ziGdswytHrcC", "Yv89iAQVPBmBPRIxsNyTq6ED0b0CHAuT");
            // 2 设置bucket的区域, COS地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
            // clientConfig中包含了设置region, https(默认http), 超时, 代理等set方法, 使用可参见源码或者接口文档FAQ中说明
            ClientConfig clientConfig = new ClientConfig(new Region("ap-chengdu"));
            // 3 生成cos客户端
            cosClient = new COSClient(cred, clientConfig);
        }
    }

    public static COSClient getCosClient() {
        return cosClient;
    }

    public static void shutdown(){
        if(null!=cosClient) cosClient.shutdown();
    }

}
