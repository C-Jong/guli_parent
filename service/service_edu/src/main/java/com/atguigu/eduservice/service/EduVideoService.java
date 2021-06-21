package com.atguigu.eduservice.service;

import com.atguigu.eduservice.entity.EduVideo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author testjava
 * @since 2021-04-12
 */
public interface EduVideoService extends IService<EduVideo> {
    //删除课程的方法
    boolean removeByCourseId(String courseId);
}
