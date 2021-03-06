package com.sidney.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sidney.eduservice.entity.EduChapter;
import com.sidney.eduservice.entity.EduVideo;
import com.sidney.eduservice.entity.chapter.ChapterVo;
import com.sidney.eduservice.entity.chapter.VideoVo;
import com.sidney.eduservice.mapper.EduChapterMapper;
import com.sidney.eduservice.service.EduChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sidney.eduservice.service.EduVideoService;
import com.sidney.exceptionhandler.GuliException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.lang.model.element.VariableElement;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author Sidney
 * @since 2020-05-17
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {

    @Autowired
    private EduVideoService videoService;

    @Override
    public List<ChapterVo> getChapterVideoByCourseId(String courseId) {
        //1.根据课程id查询所有章节
        QueryWrapper<EduChapter> wrapperChapter = new QueryWrapper<>();
        wrapperChapter.eq("course_id",courseId);
        List<EduChapter> eduChapterList = baseMapper.selectList(wrapperChapter);


        //2.根据课程id查询所有的小节
        QueryWrapper<EduVideo> wrapperVideo = new QueryWrapper<>();
        wrapperChapter.eq("course_id",courseId);
        List<EduVideo> eduVideoList = videoService.list(wrapperVideo);


        //3.遍历查询list集合进行封装
        List<ChapterVo> finalList = new ArrayList<>();
        //遍历查询章节list集合
        for (int i = 0; i < eduChapterList.size(); i++) {
         //每个章节
            EduChapter eduChapter = eduChapterList.get(i);
            ChapterVo chapterVo = new ChapterVo();
            BeanUtils.copyProperties(eduChapter,chapterVo);
            finalList.add(chapterVo);


            //封装章节的小节
            ArrayList<VideoVo> videoList = new ArrayList<>();

            //4.遍历查询小节list集合，进行封装
            for (int m = 0; m < eduVideoList.size(); m++) {
                //得到每个小节
                EduVideo eduVideo = eduVideoList.get(m);
                //判断小节里的chaapterid和章节里的id是否一样
                if (eduVideo.getChapterId().equals(eduChapter.getId())){
                    VideoVo videoVo = new VideoVo();
                    BeanUtils.copyProperties(eduVideo,videoVo);
                    videoList.add(videoVo);
                }
            }
            //把封装之后小节的list集合，放到章节对象里面
            chapterVo.setChildren(videoList);
        }


        return finalList;
    }

    @Override
    public boolean deleteChapter(String chapterId) {
        //根据chapterId章节id查询小节表，如果查询到数据，不进行删除
        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
        wrapper.eq("chapter_id",chapterId);

        int count = videoService.count(wrapper);
        //判断
        if (count>0){//查询出小节，不进行删除
           throw new GuliException(20001,"不能删除");
        }else {//不能查询数据，进行删除
         //删除
            int result = baseMapper.deleteById(chapterId);
            //成功 1>0  ture  失败0>0 fales
            return result>0;
        }

    }

    //2.根据课程id删除章节
    @Override
    public void removeChapterByCourseId(String courseId) {
        QueryWrapper<EduChapter> wrapper = new QueryWrapper<>();
        wrapper.eq("course_Id",courseId);
        baseMapper.delete(wrapper);
    }
}
