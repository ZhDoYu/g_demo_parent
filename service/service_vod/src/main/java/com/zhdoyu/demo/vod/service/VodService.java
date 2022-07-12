package com.zhdoyu.demo.vod.service;


import java.io.InputStream;

public interface VodService {

    void removeVideo(String fileId);

    String uploadVideo(InputStream inputStream, String originalFilename);
}
