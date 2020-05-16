package com.sidney.demo.excel;

import com.alibaba.excel.EasyExcel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sidney
 * @data 2020/5/14  22:39
 * @description
 */
public class TestEasyExcel {
    public static void main(String[] args) {
      /*  //实现写操作
        //1.设置写入文件夹地址和excel文件名称
        String filename="F:\\write.xlsx";
        //2.调用esayexcel里面的方法实现写操作
        //write（文件路径名，实体类class）
        EasyExcel.write(filename,DemoData.class).sheet("学生列表").doWrite(getData());*/


      //实现excel操作
        String filename="F:\\write.xlsx";
        EasyExcel.read(filename,DemoData.class,new ExcelListener()).sheet().doRead();

    }

    //创建方法返回list集合
    private  static List<DemoData> getData(){
        ArrayList<DemoData> list = new ArrayList<>();
        for (int i = 0; i <10 ; i++) {
            DemoData data = new DemoData();
            data.setSno(i);
            data.setSname("lucy"+i);
            list.add(data);
        }
        return list;
    }
}
