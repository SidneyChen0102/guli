package com.sidney.eduservice.entity.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author Sidney
 * @data 2020/5/15  23:32
 * @description
 */

@Data
public class SubjectData {

    @ExcelProperty(index = 0)
    private String  oneSubjectName;


    @ExcelProperty(index = 1)
    private String  twoSubjectName;

}
