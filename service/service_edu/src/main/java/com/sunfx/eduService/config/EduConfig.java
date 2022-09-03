package com.sunfx.eduService.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Feixiang Sun
 * @date 2021/8/17 15:47
 */
@Configuration
@MapperScan("com.sunfx.eduService.mapper")
public class EduConfig<EduCourseService> {
//    /**
//     * 逻辑删除插件!!!高版本不需要此配置
//     */
//    @Bean
//    public ISqlInjector sqlInjector() {
//        return new LogicSqlInjector();
//    }

    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}
