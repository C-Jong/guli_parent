package com.atguigu.eduservice.service;

import com.atguigu.eduservice.entity.EduCourse;
import com.atguigu.eduservice.entity.frontvo.CourseFrontVo;
import com.atguigu.eduservice.entity.frontvo.CourseWebVo;
import com.atguigu.eduservice.entity.vo.CourseInfoVo;
import com.atguigu.eduservice.entity.vo.CoursePublishVo;
import com.atguigu.eduservice.entity.vo.CourseQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author testjava
 * @since 2021-04-12
 */
public interface EduCourseService extends IService<EduCourse> {

    //添加课程信息的方法
    String saveCourseInfo(CourseInfoVo courseInfoVo);

    //根据课程id查询课程信息
    CourseInfoVo getCourseInfoById(String courseId);

    //修改课程信息
    String updateCourseInfo(CourseInfoVo courseInfoVo);

    //根据课程id查询课程确认信息
    CoursePublishVo getPublishCourseInfo(String courseId);

    //查询课程列表的方法
    void pageQuery(Page<EduCourse> pageParam, CourseQuery courseQuery);

    //删除课程的方法
    boolean removeByCourseId(String courseId);

    //1.条件查询待分页查询功能
    Map<String ,Object> getFrontCourseList(Page<EduCourse> coursePage, CourseFrontVo courseFrontVo);

    // 根据课程id查询课程信息
    CourseWebVo getBaseCourseInfo(String courseId);
}
