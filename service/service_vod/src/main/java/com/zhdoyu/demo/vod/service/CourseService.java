package com.zhdoyu.demo.vod.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhdoyu.demo.model.vod.Course;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhdoyu.demo.vo.vod.CourseFormVo;
import com.zhdoyu.demo.vo.vod.CoursePublishVo;
import com.zhdoyu.demo.vo.vod.CourseQueryVo;

import java.util.Map;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author zdy
 * @since 2022-07-10
 */
public interface CourseService extends IService<Course> {

    Map<String,Object> findPage(Page<Course> pageParam, CourseQueryVo courseQueryVo);

    void updateCourseById(CourseFormVo courseFormVo);

    Long saveCourseInfo(CourseFormVo courseFormVo);

    CourseFormVo getCourseInfoById(Long id);

    CoursePublishVo getCoursePublishVo(Long id);

    void publishCourse(Long id);

    void removeCourseById(Long id);
}
