package com.sidney.eduservice.controller;


import com.sidney.commonutils.R;
import com.sidney.eduservice.client.VodClient;
import com.sidney.eduservice.entity.EduVideo;
import com.sidney.eduservice.service.EduVideoService;
import com.sidney.exceptionhandler.GuliException;
import org.bouncycastle.cms.PasswordRecipientId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.io.PipedReader;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author Sidney
 * @since 2020-05-17
 */
@RestController
@RequestMapping("/eduservice/video")
@CrossOrigin
public class EduVideoController {
    @Autowired
    private EduVideoService videoService;

    @Autowired
    private VodClient vodClient;

    //添加小节
    @PostMapping("addVideo")
    public R addVideo(@RequestBody EduVideo eduVideo) {
        videoService.save(eduVideo);
        return R.ok();
    }

    //删除小节
    //Todo
    @DeleteMapping("{id}")
    public R deleteVideo(@PathVariable String id) {

        //根据小节id获取视频id，调用方法实现视频删除
        EduVideo eduVideo = videoService.getById(id);
        String videoSourceId = eduVideo.getVideoSourceId();

        if (!StringUtils.isEmpty(videoSourceId)){
            R result = vodClient.removeAlyVide(videoSourceId);
            if (result.getCode()==20001){
                throw new GuliException(20001,"熔断器删除视频失败");
            }

        }

        videoService.removeById(id);
        return R.ok();
    }


}

