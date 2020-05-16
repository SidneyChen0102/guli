package com.sidney.demo.excel;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * @author Sidney
 * @data 2020/5/14  22:34
 * @description
 */
public class DemoData {

    //设置excel表头名称
    @ExcelProperty(value = "学生编号",index = 0)
    private Integer sno;

    @ExcelProperty(value = "学生姓名",index = 1)
    private String sname;

    public Integer getSno() {
        return sno;
    }

    public void setSno(Integer sno) {
        this.sno = sno;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }
}
