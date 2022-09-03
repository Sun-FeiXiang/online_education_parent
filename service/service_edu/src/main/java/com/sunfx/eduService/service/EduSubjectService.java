package com.sunfx.eduService.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sunfx.eduService.entity.EduSubject;
import com.sunfx.eduService.entity.subject.OneSubject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author sunfx
 * @since 2021-09-27
 */
public interface EduSubjectService extends IService<EduSubject> {
    //添加课程分类
    void saveSubject(MultipartFile file, EduSubjectService subjectService);

    //课程分类列表（树形）
    List<OneSubject> getAllOneTwoSubject();
}
