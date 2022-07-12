package com.zhdoyu.demo.vod.controller;


import com.zhdoyu.demo.result.Result;
import com.zhdoyu.demo.vod.service.VodService;
import com.zhdoyu.demo.vod.util.ConstantPropertiesUtil;
import com.zhdoyu.demo.vod.util.Signature;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

@Api(tags = "腾讯云点播")
@RestController
@RequestMapping("/admin/vod")
public class VodController {

    @Autowired
    private VodService vodService;
    @PostMapping("upload")
    public Result uploadVideo(
            @ApiParam(name = "file", value = "文件", required = true)
            @RequestParam("file") MultipartFile file) throws IOException {
        InputStream inputStream = file.getInputStream();
        String originalFilename = file.getOriginalFilename();
        String videoId = vodService.uploadVideo(inputStream, originalFilename);
        return Result.ok(videoId);
    }

    @DeleteMapping("remove/{fileId}")
    public Result remove(@PathVariable String  fileId){
        vodService.removeVideo(fileId);
        return Result.ok(null);
    }

    @GetMapping("sign")
    public Result sign() {
        Signature sign = new Signature();
        // 设置 App 的云 API 密钥
        sign.setSecretId(ConstantPropertiesUtil.ACCESS_KEY_ID);
        sign.setSecretKey(ConstantPropertiesUtil.ACCESS_KEY_SECRET);
        sign.setCurrentTime(System.currentTimeMillis() / 1000);
        sign.setRandom(new Random().nextInt(java.lang.Integer.MAX_VALUE));
        sign.setSignValidDuration(3600 * 24 * 2); // 签名有效期：2天
        try {
            String signature = sign.getUploadSignature();
            System.out.println("signature : " + signature);
            return Result.ok(signature);
        } catch (Exception e) {
            System.out.print("获取签名失败");
            e.printStackTrace();
            return Result.fail(null);
        }
    }

}
