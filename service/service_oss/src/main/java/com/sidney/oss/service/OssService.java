package com.sidney.oss.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author Sidney
 * @data 2020/5/12  19:12
 * @description
 */


public interface OssService {
    //上传头像到oss
    String uploadFileAvatar(MultipartFile file);
}
