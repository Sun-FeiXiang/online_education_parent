package com.sunfx.eduCms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sunfx.eduCms.entity.CrmBanner;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务类
 * </p>
 *
 * @author sunfx
 * @since 2021-11-04
 */
public interface CrmBannerService extends IService<CrmBanner> {
    //查询所有banner
    List<CrmBanner> selectAllBanner();
}
