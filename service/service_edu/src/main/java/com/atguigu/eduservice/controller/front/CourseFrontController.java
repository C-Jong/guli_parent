package com.atguigu.eduservice.controller.front;

import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.EduCourse;
import com.atguigu.eduservice.entity.chapter.ChapterVo;
import com.atguigu.eduservice.entity.frontvo.CourseFrontVo;
import com.atguigu.eduservice.entity.frontvo.CourseWebVo;
import com.atguigu.eduservice.service.EduChapterService;
import com.atguigu.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin
@RequestMapping("/eduservice/coursefront")
@RestController
public class CourseFrontController {

    @Autowired
    EduCourseService courseService;

    @Autowired
    EduChapterService chapterService;

    //1.条件查询待分页查询功能
    @PostMapping("getFrontCourseList/{page}/{limit}")
    public R getFrontCourseList(
            @PathVariable Long page,
            @PathVariable Long limit,
            @RequestBody(required = false) CourseFrontVo courseFrontVo
            ){

        Page<EduCourse> coursePage = new Page<>(page,limit);
        Map<String ,Object> map = courseService.getFrontCourseList(coursePage,courseFrontVo);
        return R.ok().data(map);
    }

    //2.课程详情
    @GetMapping("getFrontCourseInfo/{courseId}")
    public R getFrontCourseInfo(@PathVariable String courseId){
        // 根据课程id查询课程信息
        CourseWebVo courseWebVo = courseService.getBaseCourseInfo(courseId);

        // 根据课程id查询章节及小节
        List<ChapterVo> chapterVideoList = chapterService.getChapterVideoByCourseId(courseId);
        return R.ok().data("courseWebVo",courseWebVo).data("chapterVideoList",chapterVideoList);
    }
}
