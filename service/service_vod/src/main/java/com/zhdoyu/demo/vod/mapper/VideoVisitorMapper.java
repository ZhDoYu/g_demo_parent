package com.zhdoyu.demo.vod.mapper;

import com.zhdoyu.demo.model.vod.VideoVisitor;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhdoyu.demo.vo.vod.VideoVisitorCountVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 视频来访者记录表 Mapper 接口
 * </p>
 *
 * @author zdy
 * @since 2022-07-10
 */
public interface VideoVisitorMapper extends BaseMapper<VideoVisitor> {

    List<VideoVisitorCountVo> findCount(@Param("courseId") Long courseId,
                                        @Param("startDate") String startDate,
                                        @Param("endDate") String endDate);
}
