package com.zhdoyu.demo.vod.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhdoyu.demo.model.vod.Teacher;
import com.zhdoyu.demo.result.Result;
import com.zhdoyu.demo.vo.vod.TeacherQueryVo;
import com.zhdoyu.demo.vod.service.TeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author zdy
 * @since 2022-07-08
 */
@Api(tags = "讲师管理接口")
@RestController
@RequestMapping("/admin/vod/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    //查询所有讲师列表
    @GetMapping("findAll")
    public Result findAll(){
        List<Teacher> list = teacherService.list();
        return Result.ok(list).message("查询数据成功");
    }

    //删除讲师
    @ApiOperation("删除讲师")
    @DeleteMapping("remove/{id}")
    public Result removeTeacherById(@ApiParam(name = "id", value = "ID", required = true)
                                  @PathVariable String id){
        boolean isSuccess = teacherService.removeById(id);
        if (isSuccess){
            return Result.ok(null);
        }else {
            return Result.fail(null);
        }

    }

    //条件查询分页列表
    @ApiOperation("条件查询分页")
    @PostMapping("findQueryPage/{current}/{limit}")
    public Result findPage(@PathVariable long current,
                           @PathVariable long limit,
                           TeacherQueryVo teacherQueryVo) {
        Page<Teacher> pageParam = new Page<>(current,limit);
        //判断teacherQueryVo对象是否为空
        if(teacherQueryVo == null) {//查询全部
            List<Teacher> list = teacherService.list();
            return Result.ok(list);
        } else {
            //获取条件值，
            String name = teacherQueryVo.getName();
            Integer level = teacherQueryVo.getLevel();
            String joinDateBegin = teacherQueryVo.getJoinDateBegin();
            String joinDateEnd = teacherQueryVo.getJoinDateEnd();
            //进行非空判断，条件封装
            QueryWrapper<Teacher> wrapper = new QueryWrapper<>();
            if(!StringUtils.isEmpty(name)) {
                wrapper.like("name",name);
            }
            if(!StringUtils.isEmpty(level)) {
                wrapper.eq("level",level);
            }
            if(!StringUtils.isEmpty(joinDateBegin)) {
                wrapper.ge("join_date",joinDateBegin);
            }
            if(!StringUtils.isEmpty(joinDateEnd)) {
                wrapper.le("join_date",joinDateEnd);
            }
            //调用方法分页查询

            //返回
            IPage<Teacher> pageModel = teacherService.page(pageParam,wrapper);
            return Result.ok(pageModel);
        }
    }

    @ApiOperation("新增")
    @PostMapping("saveTeacher")
    public Result save(@RequestBody Teacher teacher) {
        boolean isSuccess= teacherService.save(teacher);
        if (isSuccess){
            return Result.ok(null);
        }else {
            return Result.fail(null);
        }
    }

    @ApiOperation(value = "获取")
    @GetMapping("getTeacher/{id}")
    public Result get(@PathVariable Long id) {
        Teacher teacher = teacherService.getById(id);
        return Result.ok(teacher);
    }

    @ApiOperation("修改")
    @PostMapping("updateTeacher")
    public Result updateById(@RequestBody Teacher teacher) {
        boolean isSuccess = teacherService.updateById(teacher);
        if (isSuccess){
            return Result.ok(null);
        }else {
            return Result.fail(null);
        }
    }

    @ApiOperation(value = "根据id列表删除")
    @DeleteMapping("removeBatch")
    public Result batchRemove(@RequestBody List<Long> idList) {
        boolean isSuccess =teacherService.removeByIds(idList);
        if (isSuccess){
            return Result.ok(null);
        }else {
            return Result.fail(null);
        }
    }

}

