package com.zhdoyu.demo.vod.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhdoyu.demo.model.vod.Course;
import com.zhdoyu.demo.result.Result;
import com.zhdoyu.demo.vo.vod.CourseFormVo;
import com.zhdoyu.demo.vo.vod.CoursePublishVo;
import com.zhdoyu.demo.vo.vod.CourseQueryVo;
import com.zhdoyu.demo.vod.service.CourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author zdy
 * @since 2022-07-10
 */

@Api(tags = "课程管理接口")
@RestController
@RequestMapping(value="/admin/vod/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @ApiOperation(value = "获取分页列表")
    @GetMapping("{page}/{limit}")
    public Result index(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,
            @ApiParam(name = "courseVo", value = "查询对象", required = false)
                    CourseQueryVo courseQueryVo) {
        Page<Course> pageParam = new Page<>(page, limit);
        Map<String,Object> map = courseService.findPage(pageParam, courseQueryVo);
        return Result.ok(map);
    }

    //添加课程基本信息
    @ApiOperation(value = "新增")
    @PostMapping("save")
    public Result save(@RequestBody CourseFormVo courseFormVo) {
        Long courseId = courseService.saveCourseInfo(courseFormVo);
        return Result.ok(courseId);
    }

    @ApiOperation(value = "获取")
    @GetMapping("get/{id}")
    public Result get(@PathVariable Long id) {
        CourseFormVo courseFormVo = courseService.getCourseInfoById(id);
        return Result.ok(courseFormVo);
    }

    @ApiOperation(value = "修改")
    @PostMapping("update")
    public Result updateById(@RequestBody CourseFormVo courseFormVo) {
        courseService.updateCourseById(courseFormVo);
        return Result.ok(courseFormVo.getId());
    }

    //根据课程id查询发布课程信息
    @ApiOperation("id查询发布课程信息")
    @GetMapping("getCoursePublishVo/{id}")
    public Result getCoursePublishVo(@PathVariable Long id) {
        CoursePublishVo coursePublishVo = courseService.getCoursePublishVo(id);
        return Result.ok(coursePublishVo);
    }

    //课程最终发布
    @ApiOperation("课程最终发布")
    @PutMapping("publishCourse/{id}")
    public Result publishCourse(@PathVariable Long id) {
        courseService.publishCourse(id);
        return Result.ok(null);
    }

    @ApiOperation(value = "删除课程")
    @DeleteMapping("remove/{id}")
    public Result remove(@PathVariable Long id) {
        courseService.removeCourseById(id);
        return Result.ok(null);
    }


}

