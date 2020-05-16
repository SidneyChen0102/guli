package com.sidney.oss.controller;

import com.sidney.commonutils.R;
import com.sidney.oss.service.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Sidney
 * @data 2020/5/12  19:11
 * @description
 */

@RestController
@RequestMapping("/eduoss/fileoss")
@CrossOrigin
public class OssController {
    @Autowired
    private OssService ossService;
    //上传头像的方法
    @PostMapping
    public R  uploadOssFile(MultipartFile file){
        //获取上传文件
        //方法返回上传oss的路径
       String url= ossService.uploadFileAvatar(file);
       return R.ok().data("url",url);
    }

}
