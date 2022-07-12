package com.zhdoyu.demo.vod.controller;


import com.zhdoyu.demo.result.Result;
import com.zhdoyu.demo.vod.service.VideoVisitorService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 视频来访者记录表 前端控制器
 * </p>
 *
 * @author zdy
 * @since 2022-07-10
 */
@RestController
@RequestMapping("/admin/vod/videoVisitor")
public class VideoVisitorController {

    @Autowired private VideoVisitorService videoVisitorService;

    @ApiOperation("显示统计数据")
    @GetMapping("findCount/{courseId}/{startDate}/{endDate}")
    public Result findChart(
            @ApiParam("id") @PathVariable Long courseId,
            @ApiParam("开始时间") @PathVariable String startDate,
            @ApiParam("结束时间") @PathVariable String endDate){

        Map<String, Object> map = videoVisitorService.findCount(courseId, startDate, endDate);
        return Result.ok(map);
    }

}

