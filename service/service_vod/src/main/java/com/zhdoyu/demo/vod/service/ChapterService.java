package com.zhdoyu.demo.vod.service;

import com.zhdoyu.demo.model.vod.Chapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhdoyu.demo.vo.vod.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author zdy
 * @since 2022-07-10
 */
public interface ChapterService extends IService<Chapter> {

    List<ChapterVo> getNestedTreeList(Long courseId);

    void removeChapterByCourseId(Long id);
}
