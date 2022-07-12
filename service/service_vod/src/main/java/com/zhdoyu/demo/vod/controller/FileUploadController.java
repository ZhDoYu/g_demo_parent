package com.zhdoyu.demo.vod.controller;

import com.zhdoyu.demo.result.Result;
import com.zhdoyu.demo.vod.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Api(tags = "文件上传接口")
@RestController
@RequestMapping("/admin/vod/file")
@CrossOrigin
public class FileUploadController {
    @Autowired
    private FileService fileService;

    @ApiOperation("文件上传")
    @PostMapping("upload")
    public Result upload(MultipartFile file) {
        String url = fileService.upload(file);
        return Result.ok(url).message("文件上传成功");
    }
}