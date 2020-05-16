package com.sidney.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sidney.commonutils.R;
import com.sidney.eduservice.entity.EduTeacher;
import com.sidney.eduservice.entity.vo.TeacherQuery;
import com.sidney.eduservice.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.coyote.OutputBuffer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author Sidney
 * @since 2020-05-01
 */
@Api(description = "讲师管理")
@RestController
@RequestMapping("/eduservice/teacher")
@CrossOrigin
public class EduTeacherController {

    @Autowired
    private EduTeacherService teacherService;

    @ApiOperation(value = "所有讲师列表")
    //查询讲师表所有数据
    @GetMapping("/findAll")
    public R findAllTeacher() {
        List<EduTeacher> list = teacherService.list(null);
        return R.ok().data("items", list);
    }

    //逻辑删除讲师的方法
    @ApiOperation(value = "根据ID删除讲师")
    @DeleteMapping("{id}") //id需要通过路径传递
    public R removeTeacher(@ApiParam(name = "id", value = "讲师ID", required = true) @PathVariable String id) {
        boolean b = teacherService.removeById(id);

        if (b){
            return R.ok();
        }else {
            return R.error();

        }
    }

    //分页查询讲述的方法
     @GetMapping("pageTeacher/{current}/{limit}")
      public R pageListTeacher(@PathVariable long current,
                               @PathVariable long  limit){

        //创建page对象 (当前页，记录数)
         Page<EduTeacher> pageTeacher = new Page<>(current,limit);
         //调用方法的时候，底层封装，把分页所有数据封装到pageTeacher对象里面
         teacherService.page(pageTeacher, null);
         long total = pageTeacher.getTotal();//总记录数
         List<EduTeacher> records = pageTeacher.getRecords();//数据list集合
         return R.ok().data("total",total).data("row",records);
     }

     //4.条件查询带分页的方法
    @PostMapping("pageTeacher/{current}/{limit}")
    public R pageTeacherCondition(@PathVariable long current,
                                  @PathVariable long  limit,
                                  @RequestBody(required = false) TeacherQuery teacherQuery){

        //创建page对象
        Page<EduTeacher> page = new Page<>(current, limit);
        //构建条件
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();
        if (!StringUtils.isEmpty(name)){
         wrapper.like("name",name);
        }
        if (!StringUtils.isEmpty(level)){
            wrapper.eq("level",level);
        }
        if (!StringUtils.isEmpty(begin)){
            wrapper.ge("gmt_create",begin); //ge >=
        }
        if (!StringUtils.isEmpty(end)){
            wrapper.le("gmt_create",end);   //le <=
        }


        //调用方法实现分页功能
        teacherService.page(page,wrapper);
        long total = page.getTotal();//总记录数
        List<EduTeacher> records = page.getRecords();//数据list集合
        return R.ok().data("total",total).data("row",records);
    }


     //添加讲师的接口
     @PostMapping("addTeacher")
     public R addTeacher(@RequestBody EduTeacher eduTeacher){
         boolean save = teacherService.save(eduTeacher);
         if (save){
             return R.ok();
         }else {

             return R.error();
         }
     }

     //根据讲师的id进行查询
    @GetMapping("getTeacher/{id}")
    public R getTeacher(@PathVariable String id){
        EduTeacher eduTeacher = teacherService.getById(id);
        return R.ok().data("teacher",eduTeacher);
    }

    //讲师修改功能  @RequestBody和@PostMapping需要一起用
    @PostMapping("updateTeacher")
    public R updateTeacher(@RequestBody EduTeacher eduTeacher){
        boolean flag = teacherService.updateById(eduTeacher);
        if (flag){
            return R.ok();
        }else {

            return R.error();
        }
    }



}

