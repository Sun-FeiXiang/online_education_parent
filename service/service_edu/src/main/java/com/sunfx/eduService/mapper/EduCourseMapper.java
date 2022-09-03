package com.sunfx.eduService.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sunfx.eduService.entity.EduCourse;
import com.sunfx.eduService.entity.vo.CoursePublishVo;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author sunfx
 * @since 2021-10-14
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {
    CoursePublishVo getPublishCourseInfo(String courseId);
}
