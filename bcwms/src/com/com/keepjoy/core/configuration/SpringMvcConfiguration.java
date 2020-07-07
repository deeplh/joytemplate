package com.keepjoy.core.configuration;


import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.keepjoy.core.properties.KeepJoyProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import javax.servlet.MultipartConfigElement;
import java.util.ArrayList;
import java.util.List;


@Configuration
public class SpringMvcConfiguration extends WebMvcConfigurationSupport {

//    @Autowired
//    private KeepJoyInterceptor keepJoyInterceptor;
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        if(KeepJoyProperties.getUrlPatterns().length>0){
//            registry.addInterceptor(keepJoyInterceptor).addPathPatterns(KeepJoyProperties.getUrlPatterns());
//        }
//
//    }

    @Autowired
    private KeepJoyProperties keepJoyProperties;


    @Autowired
    private MultipartConfigElement multipartConfigElement;


    /**
     * 注册需要被springmvc的servlet拦截的url的规则
     *
     * @param dispatcherServlet
     * @return
     */

    public static final String[] DEFAULT_URL_MAPPING = {"/v2/api-docs",
            "/swagger-resources/configuration/security",
            "/swagger-resources/configuration/ui",
            "/swagger-resources"};

    @Bean
    public ServletRegistrationBean dispatcherRegistration(DispatcherServlet dispatcherServlet) {
        ServletRegistrationBean registration = new ServletRegistrationBean(dispatcherServlet);
//        registration.setName("keepJoyServlet");
//        registration.setName(DispatcherServletAutoConfiguration.DEFAULT_DISPATCHER_SERVLET_REGISTRATION_BEAN_NAME);

        registration.getUrlMappings().clear();

        List<String> um = keepJoyProperties.getUrlMappings();
        if (null == um) {
            um = new ArrayList<String>();
        }

        List<String> urlMappings = new ArrayList<String>();
        for (String s : um) {
            if (s.startsWith("/*")) {
                s = s.replace("/*", "*");
            }
            urlMappings.add(s);
        }
        //默认加上对swagger的映射
        for (String s : DEFAULT_URL_MAPPING) {
            if (urlMappings.indexOf(s) < 0) {
                urlMappings.add(s);
            }
        }

        registration.addUrlMappings(urlMappings.toArray(new String[urlMappings.size()]));
        registration.setMultipartConfig(multipartConfigElement);
        return registration;
    }

    @Autowired
    private FastJsonHttpMessageConverter fastJsonConverter;


    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//        for (HttpMessageConverter<?> converter : converters) {
//            if (converter instanceof MappingJackson2HttpMessageConverter){
//                converters.remove(converter);
//            }
//        }
        converters.add(fastJsonConverter);
    }

}
