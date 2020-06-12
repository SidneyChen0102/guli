package com.sidney.vod.controller;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.sidney.commonutils.R;
import com.sidney.exceptionhandler.GuliException;
import com.sidney.vod.service.VodService;
import com.sidney.vod.utils.ConstantVodUtils;
import com.sidney.vod.utils.InitVodClient;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author Sidney
 * @data 2020/5/26  17:32
 * @description
 */
@RestController
@RequestMapping("/eduvod/video")
@CrossOrigin
public class VodController {
    @Autowired
    private VodService vodService;
    @RequestMapping("test")
    public String hello(){
        return "hello";
    }
    //上传视频到阿里云
     @PostMapping("uploadAlyVideo")
    public R uploadAlyVideo(MultipartFile file) {//用MultipartFile得到上传文件
        //返回上传视频id值
         System.out.println("2222");
        String videoId = vodService.uploadVideoAly(file);

        return R.ok().data("videoId", videoId);
    }

    //根据视频id删除阿里云视频
    @DeleteMapping("removeAlyVideo/{id}")
    public R removeAlyVide(@PathVariable String id){

        try {
            //初始化对象
            DefaultAcsClient client = InitVodClient.initVodClient(ConstantVodUtils.ACCESS_KEY_ID, ConstantVodUtils.ACCESS_KEY_SECRET);
            //创建删除视频request对象
            DeleteVideoRequest request = new DeleteVideoRequest();
            //requset设置视频id
            request.setVideoIds(id);
            //调用初始化对象的方法实现删除
            client.getAcsResponse(request);
            return R.ok();
        }catch (Exception e){
           throw  new GuliException(20001,"删除视频失败");
        }

    }


    //删除多个阿里云视频的方法

    @DeleteMapping("delete-batch")
    public R deleteBatch(@RequestParam("videoIdList") List videoIdList){
        vodService.removeMoreAlyVideo(videoIdList);
        return R.ok() ;
    }

}
