package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.R;
import com.atguigu.eduservice.client.VodClient;
import com.atguigu.eduservice.entity.EduVideo;
import com.atguigu.eduservice.service.EduVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-04-12
 */
@RestController
@RequestMapping("/eduservice/video")
@CrossOrigin
public class EduVideoController {

    @Autowired
    private EduVideoService videoService;

    @Autowired
    private VodClient vodClient;

    //根据小节id查询小节
    @GetMapping("getVideo/{videoId}")
    public R getChapter(@PathVariable String videoId){
        EduVideo eduVideo = videoService.getById(videoId);
        return R.ok().data("eduVideo",eduVideo);
    }

    //添加小节
    @PostMapping("addVideo")
    public R addVideo(@RequestBody EduVideo eduVideo){
        videoService.save(eduVideo);
        return R.ok();
    }

    //删除小节并删除对应的阿里云视频
    @DeleteMapping("{videoId}")
    public R deleteVideo(@PathVariable String videoId){
        EduVideo eduVideo = videoService.getById(videoId);//根据id查询小节
        String videoSourceId = eduVideo.getVideoSourceId();//获取小节视频id
        if (!StringUtils.isEmpty(videoSourceId)){//视频id不为空，将其删除
            vodClient.removeVideo(videoSourceId);
        }
        videoService.removeById(videoId);//删除小节信息
        return R.ok();
    }


    //修改小节
    @PostMapping("updateVideo")
    public R updateChapter(@RequestBody EduVideo eduVideo){
        videoService.updateById(eduVideo);
        return R.ok().message("视频删除成功");
    }

}

