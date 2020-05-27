package com.sidney.eduservice.entity.vo;

import lombok.Data;

/**
 * @author Sidney
 * @data 2020/5/24  0:07
 * @description
 */
@Data
public class CoursePublishVo {
    private String id;
    private String title;
    private String cover;
    private Integer lessonNum;
    private String subjectLevelOne;
    private String subjectLevelTwo;
    private String teacherName;
    private String price;//只用于显示
}
