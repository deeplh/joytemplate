package com.keepjoy.core.configuration;

import com.keepjoy.core.properties.KeepJoyProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.MultipartConfigElement;

@Configuration
public class UploadConfiguration {

    @Autowired
    public KeepJoyProperties keepJoyProperties;

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize(keepJoyProperties.getMaxFileSize());
        factory.setMaxRequestSize(keepJoyProperties.getMaxRequestSize());
        return factory.createMultipartConfig();
    }

}
