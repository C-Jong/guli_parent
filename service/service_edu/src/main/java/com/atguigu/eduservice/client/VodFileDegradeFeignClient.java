package com.atguigu.eduservice.client;

import com.atguigu.commonutils.R;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VodFileDegradeFeignClient implements VodClient{
    @Override
    public R removeVideo(String id) {
        return R.error().message("time out");
    }

    @Override
    public R removeVideoList(List<String> videoList) {
        return R.error().message("time out");
    }
}
