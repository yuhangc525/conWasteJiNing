package cn.edu.bjtu.jzlj.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @program:
 * @description: mybatisplus配置类
 * @author:
 * @create: 2019-09-24 17:52
 **/
@EnableTransactionManagement
@Configuration
public class MybatisPlusConfig {
    /**
    * @Author: Administrator
    * @Description: 分页插件生效配置
    * @Date 2020/11/8 19:35
    * @Param
    * @return com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor
    * @throws:
     **/
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}
