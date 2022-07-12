package com.zhdoyu.demo.vod.mapper;

import com.zhdoyu.demo.model.vod.Course;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhdoyu.demo.vo.vod.CoursePublishVo;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author zdy
 * @since 2022-07-10
 */
public interface CourseMapper extends BaseMapper<Course> {


    CoursePublishVo selectCoursePublishVoById(Long id);
}
