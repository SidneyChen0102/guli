package com.sidney.oss.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Sidney
 * @data 2020/5/12  18:13
 * @description 读取配置文件中的固定值
 */
@Component
public class ConstantPropertiesUtils implements InitializingBean {//初始化的时候，接口会交给spring管理，并用注解方式将配置文件中的值读取出来，赋值给以下几个属性，并在之后执行afterPropertiesSet()
    //读取配置文件的内容
    @Value("${aliyun.oss.file.endpoint}") //属性注入 用spring中的value注解可以读取配置文件中的内容
    private String endpoint;

    @Value("${aliyun.oss.file.keyid}")
    private String keyId;

    @Value("${aliyun.oss.file.keysecret}")
    private String keySecret;

    @Value("${aliyun.oss.file.bucketname}")
    private String bucketName;


    //定义公开静态类常量
    public static String END_POIND;

    public static String ACCESS_KEY_ID;
    public static String ACCESS_KEY_SECRET;
    public static String BUCKET_NAME;

    @Override
    public void afterPropertiesSet() throws Exception {
        END_POIND=endpoint;
        ACCESS_KEY_ID = keyId;
        ACCESS_KEY_SECRET = keySecret;
        BUCKET_NAME = bucketName;
    }
}
