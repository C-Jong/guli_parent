package com.atguigu.vod.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface VideoService {
    //视频上传
    String uploadVideo(MultipartFile file);

    //根据id删除视频
    void removeVideo(String id);

    //删除多个视频的方法
    void removeMoreAlyVideo(List videoList);
}
