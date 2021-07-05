package com.atguigu.eduservice.controller.front;

import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.EduCourse;
import com.atguigu.eduservice.entity.EduTeacher;
import com.atguigu.eduservice.service.EduCourseService;
import com.atguigu.eduservice.service.EduTeacherService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RequestMapping("/eduservice/teacherfront")
@RestController
public class TeacherFrontController {

    @Autowired
    private EduTeacherService teacherService;

    @Autowired
    private EduCourseService courseService;


    //1.分页查询讲师
    @PostMapping("getTeacherFrontList/{page}/{limit}")
    public R pageTeacher(@PathVariable Long page,
                         @PathVariable Long limit){
        Page<EduTeacher> teacherPage = new Page<>(page,limit);
        teacherService.page(teacherPage,null);
        Long total = teacherPage.getTotal();
        List<EduTeacher> list = teacherPage.getRecords();
        return R.ok().data("total",total).data("items",list);
    }

    //2.讲师详情
    @GetMapping("getAllTeachers/{id}")
    public R getAllTeachers(@PathVariable String id){

        //讲师信息
        EduTeacher eduTeacher = teacherService.getById(id);


        //讲师所讲课程
        QueryWrapper<EduCourse> wrapperCourse = new QueryWrapper<>();
        wrapperCourse.eq("teacher_id",id);
        List<EduCourse> courseList = courseService.list(wrapperCourse);

        return R.ok().data("teacher",eduTeacher).data("courseList",courseList);
    }




}
