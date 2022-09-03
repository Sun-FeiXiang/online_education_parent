package com.sunfx.eduService.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sunfx.eduService.entity.EduCourse;
import com.sunfx.eduService.entity.EduCourseDescription;
import com.sunfx.eduService.entity.vo.CourseInfoVo;
import com.sunfx.eduService.entity.vo.CoursePublishVo;
import com.sunfx.eduService.entity.vo.CourseQueryVo;
import com.sunfx.eduService.mapper.EduCourseMapper;
import com.sunfx.eduService.service.EduChapterService;
import com.sunfx.eduService.service.EduCourseDescriptionService;
import com.sunfx.eduService.service.EduCourseService;
import com.sunfx.eduService.service.EduVideoService;
import com.sunfx.servicebase.execptionhandler.EduException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author sunfx
 * @since 2021-10-14
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {
    //课程描述注入
    @Autowired
    private EduCourseDescriptionService courseDescriptionService;
    //注入小节和章节service
    @Autowired
    private EduVideoService eduVideoService;

    @Autowired
    private EduChapterService chapterService;

    //添加课程基本信息的方法
    @Override
    public String saveCourseInfo(CourseInfoVo courseInfoVo) {
        //1 向课程表添加课程基本信息
        //CourseInfoVo对象转换eduCourse对象
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo, eduCourse);
        int insert = baseMapper.insert(eduCourse);
        if (insert == 0) {
            //添加失败
            throw new EduException(20001, "添加课程信息失败");
        }

        //获取添加之后课程id
        String cid = eduCourse.getId();

        //2 向课程简介表添加课程简介
        //edu_course_description
        EduCourseDescription courseDescription = new EduCourseDescription();
        courseDescription.setDescription(courseInfoVo.getDescription());
        //设置描述id就是课程id
        courseDescription.setId(cid);
        courseDescriptionService.save(courseDescription);

        return cid;
    }

    //根据课程id查询课程基本信息
    @Override
    public CourseInfoVo getCourseInfo(String courseId) {
        //1 查询课程表
        EduCourse eduCourse = baseMapper.selectById(courseId);
        CourseInfoVo courseInfoVo = new CourseInfoVo();
        BeanUtils.copyProperties(eduCourse, courseInfoVo);

        //2 查询描述表
        EduCourseDescription courseDescription = courseDescriptionService.getById(courseId);
        courseInfoVo.setDescription(courseDescription.getDescription());

        return courseInfoVo;
    }

    //修改课程信息
    @Override
    public void updateCourseInfo(CourseInfoVo courseInfoVo) {
        //1 修改课程表
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo, eduCourse);
        int update = baseMapper.updateById(eduCourse);
        if (update == 0) {
            throw new EduException(20001, "修改课程信息失败");
        }

        //2 修改描述表
        EduCourseDescription description = new EduCourseDescription();
        description.setId(courseInfoVo.getId());
        description.setDescription(courseInfoVo.getDescription());
        courseDescriptionService.updateById(description);
    }

    //根据课程id查询课程确认信息
    @Override
    public CoursePublishVo publishCourseInfo(String id) {
        //调用mapper
        CoursePublishVo publishCourseInfo = baseMapper.getPublishCourseInfo(id);
        return publishCourseInfo;
    }

    //删除课程
    @Override
    public void removeCourse(String courseId) {
        //1 根据课程id删除小节
        eduVideoService.removeVideoByCourseId(courseId);

        //2 根据课程id删除章节
        chapterService.removeChapterByCourseId(courseId);

        //3 根据课程id删除描述
        courseDescriptionService.removeById(courseId);

        //4 根据课程id删除课程本身
        int result = baseMapper.deleteById(courseId);
        if (result == 0) { //失败返回
            throw new EduException(20001, "删除失败");
        }
    }

    /**
     * 分页查询讲师列表
     *
     * @param current
     * @param limit
     * @return
     */
    @Override
    public Map queryCourseByPage(Long current, Long limit, CourseQueryVo courseQueryVO) {

        Page<EduCourse> pageCourse = new Page<>(current, limit);
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(courseQueryVO.getTitle())) {
            wrapper.like("title", courseQueryVO.getTitle());
        }
        if (courseQueryVO.getStatus() != null) {
            wrapper.eq("status", courseQueryVO.getStatus());
        }
        if (!StringUtils.isEmpty(courseQueryVO.getBegin())) {
            wrapper.ge("gmt_create", courseQueryVO.getBegin());
        }
        if (!StringUtils.isEmpty(courseQueryVO.getEnd())) {
            wrapper.le("gmt_create", courseQueryVO.getEnd());
        }
        wrapper.orderByDesc("gmt_modified");
        Page<EduCourse> coursePage = page(pageCourse, wrapper);
        long total = coursePage.getTotal();
        List<EduCourse> courses = coursePage.getRecords();
        Map map = new HashMap(2);
        map.put("total", total);
        map.put("courseList", courses);

        return map;
    }
}
