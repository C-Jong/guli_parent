package com.atguigu.eduservice.controller.front;

import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.EduCourse;
import com.atguigu.eduservice.entity.frontvo.CourseFrontVo;
import com.atguigu.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin
@RequestMapping("/eduservice/coursefront")
@RestController
public class CourseFrontController {

    @Autowired
    EduCourseService courseService;

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

}
