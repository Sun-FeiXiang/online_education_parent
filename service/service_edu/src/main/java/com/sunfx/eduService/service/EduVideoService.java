package com.sunfx.eduService.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sunfx.eduService.entity.EduVideo;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author sunfx
 * @since 2021-10-14
 */
public interface EduVideoService extends IService<EduVideo> {
    //1 根据课程id删除小节
    void removeVideoByCourseId(String courseId);
}
