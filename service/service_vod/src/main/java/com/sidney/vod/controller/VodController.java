package com.sidney.vod.controller;

import com.sidney.commonutils.R;
import com.sidney.vod.service.VodService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Sidney
 * @data 2020/5/26  17:32
 * @description
 */

@Api(description="阿里云视频点播微服务")
@RestController
@RequestMapping("/eduvod/video")
@CrossOrigin
public class VodController {

    @Autowired
    private VodService vodService;


    //上传视频到阿里云
    @PostMapping("uploadAlyVideo")
    public R uploadAlyVideo(MultipartFile file){//用MultipartFile得到上传文件
        //返回上传视频id值
        String videoId= vodService.uploadVideoAly(file);
        return R.ok().data("videoId",videoId);
    }

}
