package com.sidney.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.event.SyncReadListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sidney.eduservice.entity.EduSubject;
import com.sidney.eduservice.entity.excel.SubjectData;
import com.sidney.eduservice.entity.subject.OneSubject;
import com.sidney.eduservice.entity.subject.TwoSubject;
import com.sidney.eduservice.listener.SubjectExcelListener;
import com.sidney.eduservice.mapper.EduSubjectMapper;
import com.sidney.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author Sidney
 * @since 2020-05-15
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {


    //添加课程分类
    @Override
    public void saveSubject(MultipartFile file,EduSubjectService subjectService) {


        try {
            InputStream in = file.getInputStream();
            EasyExcel.read(in, SubjectData.class,new SubjectExcelListener(subjectService)).sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public List<OneSubject> getAllOneTwoSubject() {
        //1.查询所有的一级分类  parentid=0
        QueryWrapper<EduSubject> wrapperOne = new QueryWrapper<>();
        wrapperOne.eq("parent_id","0");
        //在servcie中调mapper
        List<EduSubject> oneSubjectList = baseMapper.selectList(wrapperOne);
        // this.list(wrapperOne)

        //2.查询所有的二级分类   parentid！=0
        QueryWrapper<EduSubject> wrapperTwo = new QueryWrapper<>();
        wrapperOne.ne("parent_id","0");
        List<EduSubject> twoSubjectList = baseMapper.selectList(wrapperTwo);


        //存储最终封装数据
        ArrayList<OneSubject> finalSubjectList = new ArrayList<>();


        //3.封装一级分类
         //查询出来的一级分类list集合遍历，得到每个一级分类的对象
        for (int i = 0; i < oneSubjectList.size(); i++) {
            EduSubject eduSubject = oneSubjectList.get(i);

            //把eduSubject里面值获取出来，放到OneSubject对象里面
            OneSubject oneSubject = new OneSubject();
            //spring中的工具类，把一个对象中的值复制到另一个对象中
            BeanUtils.copyProperties(eduSubject,oneSubject);
            finalSubjectList.add(oneSubject);


            //4.封装二级分类
            //在一级分类循环遍历查询所有的二级分类
            List<TwoSubject> twoFinalSubjectList=new ArrayList<>();
            for (int m = 0; m < twoSubjectList.size(); m++) {
                EduSubject tSubject = twoSubjectList.get(m);
                //判断二级分类属于哪个一级分类，二级分类的pid和一级分类的id是否一样，如果一样，则属于该一级分类
                if (tSubject.getParentId().equals(eduSubject.getId())){
                    TwoSubject twoSubject = new TwoSubject();
                    BeanUtils.copyProperties(tSubject,twoSubject);
                    twoFinalSubjectList.add(twoSubject);
                }

            }

           //把一级分类下面所有的二级分类放到一级分类里面
            oneSubject.setChildren(twoFinalSubjectList);

        }



        return finalSubjectList;
    }


}
