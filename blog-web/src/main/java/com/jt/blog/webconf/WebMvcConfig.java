package com.jt.blog.webconf;

import com.jt.blog.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.io.File;

/**
 * @author : 戴瑞
 * @create 2016-08-29 13
 **/
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    /**
     *  文件的上传路径也是文件的路径
     */
    @Value("${upload-file-path}")
    private String commonFilePath;

    /**
     *  ckEdit文件的上传路径
     */
    @Value("${upload-blogFile-path}")
    private String blogFilePath;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/back/**").excludePathPatterns("/back/login/**", "/back/logout/**");
        super.addInterceptors(registry);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/image/**").addResourceLocations("file:"+commonFilePath+ File.separator);
        registry.addResourceHandler("/blog/img/**").addResourceLocations("file:"+blogFilePath+ File.separator);
        super.addResourceHandlers(registry);
    }
}

