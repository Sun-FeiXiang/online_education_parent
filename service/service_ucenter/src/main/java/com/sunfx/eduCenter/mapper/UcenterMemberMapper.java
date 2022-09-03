package com.sunfx.eduCenter.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sunfx.eduCenter.entity.UcenterMember;

/**
 * <p>
 * 会员表 Mapper 接口
 * </p>
 *
 * @author sunfx
 * @since 2021-11-09
 */
public interface UcenterMemberMapper extends BaseMapper<UcenterMember> {
    //查询某一天注册人数
    Integer countRegisterDay(String day);
}
