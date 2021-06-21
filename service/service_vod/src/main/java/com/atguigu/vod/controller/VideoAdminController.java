package com.atguigu.vod.controller;

import com.atguigu.commonutils.R;
import com.atguigu.vod.service.VideoService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/eduvod/video")
@CrossOrigin
public class VideoAdminController {
    @Autowired
    private VideoService videoService;

    //上传视频
    @PostMapping("upload")
    public R uploadVideo(
            @ApiParam(name = "file", value = "文件", required = true)
            @RequestParam("file") MultipartFile file) throws Exception {

        String videoId = videoService.uploadVideo(file);
        return R.ok().message("视频上传成功").data("videoId", videoId);
    }

    //根据视频id删除视频
    @DeleteMapping("{id}")
    public R removeVideo(@PathVariable String id){
        videoService.removeVideo(id);
        return R.ok();
    }

    //删除多个视频的方法
    @DeleteMapping("delete-batch")
    public R removeVideoList(@RequestParam List videoList){
        videoService.removeMoreAlyVideo(videoList);
        return R.ok();
    }
}
