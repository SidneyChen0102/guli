package com.sidney.eduservice.service.impl;

import com.sidney.eduservice.entity.EduCourse;
import com.sidney.eduservice.entity.EduCourseDescription;
import com.sidney.eduservice.entity.vo.CourseInfoVo;
import com.sidney.eduservice.mapper.EduCourseMapper;
import com.sidney.eduservice.service.EduCourseDescriptionService;
import com.sidney.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sidney.exceptionhandler.GuliException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
