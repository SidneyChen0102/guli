package com.sidney.eduservice.service.impl;

import com.sidney.eduservice.entity.EduChapter;
import com.sidney.eduservice.entity.EduCourse;
import com.sidney.eduservice.entity.EduCourseDescription;
import com.sidney.eduservice.entity.EduVideo;
import com.sidney.eduservice.entity.vo.CourseInfoVo;
import com.sidney.eduservice.entity.vo.CoursePublishVo;
import com.sidney.eduservice.mapper.EduCourseMapper;
import com.sidney.eduservice.service.EduChapterService;
import com.sidney.eduservice.service.EduCourseDescriptionService;
import com.sidney.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sidney.eduservice.service.EduVideoService;
import com.sidney.exceptionhandler.GuliException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.PipedReader;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author Sidney
 * @since 2020-05-17
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {
    //课程描述的注入
    @Autowired
    private EduCourseDescriptionService courseDescriptionService;

    //注入小节和章节
    @Autowired
    private EduVideoService eduVideoService;

    @Autowired
    private EduChapterService eduChapterService;




   //添加课程信息基本的方法
    @Override
    public String saveCourseInfo(CourseInfoVo courseInfoVo) {
       //1.向课程表添加课程基本信息
        //courseInfoVo对象转换eduCourse对象
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo,eduCourse);
        int insert = baseMapper.insert(eduCourse);

        if (insert==0){
            throw new GuliException(20001,"添加课程信息失败");
        }

        //获取添加之后课程id
        String cid = eduCourse.getId();

        //2.向课程简介表添加课程简介
        EduCourseDescription courseDescription = new EduCourseDescription();
        courseDescription.setDescription(courseInfoVo.getDescription());

        //设置描述id就是课程id
        courseDescription.setId(cid);
        courseDescriptionService.save(courseDescription);


        return  cid;
    }


    //根据id查询课程基本信息，涉及到两张表
    @Override
    public CourseInfoVo getCourseInfo(String courseId) {
        //1.查询课程表
        EduCourse eduCourse = baseMapper.selectById(courseId);
        CourseInfoVo courseInfoVo = new CourseInfoVo();
        BeanUtils.copyProperties(eduCourse,courseInfoVo);


        //2.查询描述表
        EduCourseDescription courseDescription = courseDescriptionService.getById(courseId);
        courseInfoVo.setDescription(courseDescription.getDescription());
        return courseInfoVo;
    }


    //修改课程信息
    @Override
    public void updateCourseInfo(CourseInfoVo courseInfoVo) {
     //1.修改课程表
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo,eduCourse);
        int update = baseMapper.updateById(eduCourse);
         if(update==0){
             throw new GuliException(20001,"修改课程信息失败");
         }


         //2.修改描述信息
        EduCourseDescription description = new EduCourseDescription();
         description.setId(courseInfoVo.getId());
         description.setDescription(courseInfoVo.getDescription());
        courseDescriptionService.updateById(description);

    }

    //根据课程id查询课程确认信息
    @Override
    public CoursePublishVo getPublishCourseInfo(String id) {
        //调用mapper
        CoursePublishVo publishCorseInfo = baseMapper.getPublishCorseInfo(id);
        return publishCorseInfo;
    }

    @Override
    public void removeCourse(String courseId) {
         //1.根据课程id删除小节
         eduVideoService.removeVideoByCourseId(courseId);

         //2.根据课程id删除章节
         eduChapterService.removeChapterByCourseId(courseId);

        //3.根据课程id删除描述 描述id和课程id一对一，是一个id，所以用removeById
        courseDescriptionService.removeById(courseId);

        //4.根据课程id删除课程本身
        int result = baseMapper.deleteById(courseId);
        if (result==0){//失败返回
            throw new GuliException(20001,"删除失败");
        }
    }
}
