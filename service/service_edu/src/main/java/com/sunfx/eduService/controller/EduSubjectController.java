package com.sunfx.eduService.controller;


import com.sunfx.commonutils.R;
import com.sunfx.eduService.entity.subject.OneSubject;
import com.sunfx.eduService.service.EduSubjectService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author sunfx
 * @since 2021-09-27
 */
@Api(tags = "课程分类管理")
@RestController
@RequestMapping("/eduService/subject")
@CrossOrigin  //解决跨域
public class EduSubjectController {
    @Autowired
    private EduSubjectService subjectService;

    /*添加课程分类，获取上传过来的文件，把文件内容读取出来*/
    @PostMapping("addSubject")
    public R addSubject(MultipartFile file) {
        //上传过来excel文件
        subjectService.saveSubject(file, subjectService);
        return R.ok();
    }

    //课程分类列表（树形）
    @GetMapping("getAllSubject")
    public R getAllSubject() {
        //list集合泛型是一级分类
        List<OneSubject> list = subjectService.getAllOneTwoSubject();
        return R.ok().data("tree", list);
    }

}

