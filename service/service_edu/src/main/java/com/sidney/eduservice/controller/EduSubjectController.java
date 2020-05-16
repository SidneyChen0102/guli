package com.sidney.eduservice.controller;


import com.sidney.commonutils.R;
import com.sidney.eduservice.entity.EduSubject;
import com.sidney.eduservice.entity.subject.OneSubject;
import com.sidney.eduservice.service.EduSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author Sidney
 * @since 2020-05-15
 */
@RestController
@RequestMapping("/eduservice/subject")
@CrossOrigin
public class EduSubjectController {

    @Autowired
    private EduSubjectService subjectService;
    //添加课程分类
    //获取上传过来文件，把文件内容读取出来
    @PostMapping("addSubject")
    public R addSubject(MultipartFile file){
        //上传过来excel文件
        subjectService.saveSubject(file,subjectService);
        return R.ok();
    }


    //课程分类
    @GetMapping("getAllSubject")
    public R  getAllSubject(){
        //list集合泛型使一级分类
        List<OneSubject> list =subjectService.getAllOneTwoSubject();
        return R.ok().data("list",list);
    }
}

