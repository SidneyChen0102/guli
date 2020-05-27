package com.sidney.eduservice.controller;


import com.sidney.commonutils.R;
import com.sidney.eduservice.entity.EduVideo;
import com.sidney.eduservice.service.EduVideoService;
import org.springframework.beans.factory.annotation.Autowired;
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
        videoService.removeById(id);
        return R.ok();
    }


}

