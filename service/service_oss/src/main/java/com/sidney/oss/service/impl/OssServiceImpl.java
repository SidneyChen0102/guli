package com.sidney.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.sidney.oss.service.OssService;
import com.sidney.oss.utils.ConstantPropertiesUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @author Sidney
 * @data 2020/5/12  19:13
 * @description
 */
@Service
public class OssServiceImpl implements OssService {
    @Override
    public String uploadFileAvatar(MultipartFile file) {
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = ConstantPropertiesUtils.END_POIND;
        // 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，创建并使用RAM子账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建。
        String accessKeyId = ConstantPropertiesUtils.ACCESS_KEY_ID;
        String accessKeySecret = ConstantPropertiesUtils.ACCESS_KEY_SECRET;
        String bucketName = ConstantPropertiesUtils.BUCKET_NAME;


        // 上传文件流。
        InputStream inputStream = null;
        try {
            // 创建OSSClient实例。
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
            inputStream = file.getInputStream();

            //获取文件名称
            String filename = file.getOriginalFilename();

            //1.在文件名称里面添加随机唯一的值,避免同名文件的覆盖
            String uuid = UUID.randomUUID().toString().replace("-","");
            filename=uuid+filename;


            //2 把文件按照日期进行分类
            //获取当前日期
            String datePath = new DateTime().toString("yyyy/MM/dd");

            filename=datePath+"/"+filename;


            //第一个参数 Bucket名称 第二个参数 上传到0ss文件路径和文件名称   第三个参数 上传文件的输入流
            ossClient.putObject(bucketName, filename, inputStream);

            // 关闭OSSClient。
            ossClient.shutdown();

            //把上传之后文件路径返回
            //需要把上传到阿里云oss路径手动拼接起来
            String url="https://"+bucketName+"."+endpoint+"/"+filename;
            return url;



        }    catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
