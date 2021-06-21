package com.atguigu.eduservice.client;

import com.atguigu.commonutils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "service-vod",fallback = VodFileDegradeFeignClient.class)
@Component
public interface VodClient {

    //根据视频id删除视频
    @DeleteMapping("/eduvod/video/{id}")
    public R removeVideo(@PathVariable("id") String id);

    //删除多个视频的方法
    @DeleteMapping("/eduvod/video/delete-batch")
    public R removeVideoList(@RequestParam List<String> videoList);

}
