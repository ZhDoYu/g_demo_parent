package com.zhdoyu.demo.vod.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.zhdoyu.demo.model.vod.Video;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author zdy
 * @since 2022-07-10
 */
public interface VideoService extends IService<Video> {

    void removeVideoByCourseId(Long id);

    void removeVideoById(Long id);
}
