package com.sidney.eduservice.entity.chapter;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sidney
 * @data 2020/5/18  22:22
 * @description
 */

@Data
public class ChapterVo {
    private String id;

    private String title;
    //表示小节
    private List<VideoVo> children=new ArrayList<>();
}
