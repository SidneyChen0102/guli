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
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
