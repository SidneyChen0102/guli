package com.sidney.vod.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author Sidney
 * @data 2020/5/26  17:35
 * @description
 */
public interface VodService {
    //上传视频到阿里云
    String uploadVideoAly(MultipartFile file);

    //删除多个阿里云视频
    void removeMoreAlyVideo(List videoIdList);
}
