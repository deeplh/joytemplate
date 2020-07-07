package com.bcwms;

import com.bcwms.properties.BcwProperties;
import com.keepjoy.core.properties.KeepJoyProperties;
import com.keepjoy.security.properties.SecurityProperties;
import com.keepjoy.weixin.properties.WeixinProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@ComponentScan(basePackages = {"com.keepjoy", "com.bcwms"})
@EntityScan(basePackages = {"com.keepjoy.core.entity", "com.keepjoy.weixin.entity", "com.keepjoy.security.entity", "com.bcwms.entity"})
@EnableConfigurationProperties({KeepJoyProperties.class, SecurityProperties.class, BcwProperties.class, WeixinProperties.class})
@EnableTransactionManagement
@EnableAsync//开启异步线程支持
@SpringBootApplication
//@ServletComponentScan(basePackages = {"com.keepjoy.core.module.druid.servlet.DruidStatViewServlet"})
public class BcwApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(BcwApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(BcwApplication.class);
    }



}
