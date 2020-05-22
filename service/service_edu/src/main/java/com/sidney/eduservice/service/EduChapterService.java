package com.sidney.eduservice.service;

import com.sidney.eduservice.entity.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sidney.eduservice.entity.chapter.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author Sidney
 * @since 2020-05-17
 */
public interface EduChapterService extends IService<EduChapter> {
    List<ChapterVo> getChapterVideoByCourseId(String courseId);

    boolean deleteChapter(String chapterId);
}
