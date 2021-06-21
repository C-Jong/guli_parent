package com.atguigu.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.atguigu.eduservice.entity.EduSubject;
import com.atguigu.eduservice.entity.excel.SubjectData;
import com.atguigu.eduservice.entity.subject.OneSubject;
import com.atguigu.eduservice.entity.subject.TwoSubject;
import com.atguigu.eduservice.listener.SubjectExcelListener;
import com.atguigu.eduservice.mapper.EduSubjectMapper;
import com.atguigu.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-04-10
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {


    //添加课程分类的方法
    @Override
    public void saveSubject(MultipartFile file, EduSubjectService eduSubjectService) {
        try{
            //文件输入流
            InputStream in = file.getInputStream();
            //调用方法进行读取
            EasyExcel.read(in, SubjectData.class,new SubjectExcelListener(eduSubjectService)).sheet().doRead();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //课程分类列表（树形）
    @Override
    public List<OneSubject> getOneTwoSubject() {

        //1.查询一级分类(parent_id = 0)
        QueryWrapper<EduSubject> wrapperOne = new QueryWrapper<>();
        wrapperOne.eq("parent_id","0");
        List<EduSubject> eduSubjectsOne = baseMapper.selectList(wrapperOne);

        //2.查询二级分类(parent_id != 0)
        QueryWrapper<EduSubject> wrapperTwo = new QueryWrapper<>();
        wrapperOne.ne("parent_id","0");
        List<EduSubject> eduSubjectsTwo = baseMapper.selectList(wrapperTwo);

        //创建list集合，存储最终返回的数据
        List<OneSubject> finalList = new ArrayList<>();


        //3.封装一级分类
        for (EduSubject eduSubjectOne: eduSubjectsOne){
            OneSubject one = new OneSubject();

            //one.setId(eduSubject.getId());
            //one.setTitle(eduSubject.getTitle());
            BeanUtils.copyProperties(eduSubjectOne,one);//作用与上两行代码作用相同


            List<TwoSubject> two = new ArrayList<>();
            //4.封装二级分类
            for(EduSubject eduSubjectTwo:eduSubjectsTwo){
                TwoSubject twoSubject = new TwoSubject();
                if (eduSubjectTwo.getParentId().equals(eduSubjectOne.getId())){
                    BeanUtils.copyProperties(eduSubjectTwo,twoSubject);
                    two.add(twoSubject);
                }
                one.setChildren(two);
            }
            finalList.add(one);
        }

        return finalList;
    }

}
