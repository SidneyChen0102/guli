package com.sidney.eduservice.service;

import com.sidney.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sidney.eduservice.entity.vo.CourseInfoVo;
import com.sidney.eduservice.entity.vo.CoursePublishVo;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author Sidney
 * @since 2020-05-17
 */


public interface EduCourseService extends IService<EduCourse> {

    String saveCourseInfo(CourseInfoVo courseInfoVo);

    CourseInfoVo getCourseInfo(String courseId);

    void updateCourseInfo(CourseInfoVo courseInfoVo);

    CoursePublishVo getPublishCourseInfo(String id);

    void removeCourse(String courseId);
}
