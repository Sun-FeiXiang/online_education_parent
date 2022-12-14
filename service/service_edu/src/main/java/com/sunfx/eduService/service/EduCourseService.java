package com.sunfx.eduService.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sunfx.eduService.entity.EduCourse;
import com.sunfx.eduService.entity.vo.CourseInfoVo;
import com.sunfx.eduService.entity.vo.CoursePublishVo;
import com.sunfx.eduService.entity.vo.CourseQueryVo;

import java.util.Map;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author sunfx
 * @since 2021-10-14
 */
public interface EduCourseService extends IService<EduCourse> {
    //添加课程基本信息的方法
    String saveCourseInfo(CourseInfoVo courseInfoVo);

    //根据课程id查询课程基本信息
    CourseInfoVo getCourseInfo(String courseId);

    //修改课程信息
    void updateCourseInfo(CourseInfoVo courseInfoVo);

    //根据课程id查询课程确认信息
    CoursePublishVo publishCourseInfo(String id);

    //删除课程
    void removeCourse(String courseId);

    //分页查询讲师列表
    Map queryCourseByPage(Long current, Long limit, CourseQueryVo courseQueryVO);
}
