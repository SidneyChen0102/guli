package com.sidney.eduservice.entity.subject;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sidney
 * @data 2020/5/16  17:17
 * @description  一级分类
 */

//一级分类

    @Data
public class OneSubject {
   private String id;
   private String title;

   //一个一级分类有多个二级分类
    private List<TwoSubject> children=new ArrayList<>();


}

