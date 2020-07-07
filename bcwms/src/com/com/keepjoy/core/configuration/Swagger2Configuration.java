package com.keepjoy.core.configuration;


import com.keepjoy.core.properties.KeepJoyProperties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.paths.AbstractPathProvider;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

@Configuration
@EnableSwagger2
public class Swagger2Configuration {


    @Autowired
    public KeepJoyProperties keepJoyProperties;

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .pathProvider(new CustRelativePathProvider())
                .select()
                .apis(RequestHandlerSelectors.basePackage(keepJoyProperties.getSwaggerBasePackage()))
                .paths(PathSelectors.any())
                .build();
    }
    //构建 api文档的详细信息函数,注意这里的注解引用的是哪个
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //页面标题
                .title("接口测试")
                //版本号
                .version("1.0")
                //描述
                .description("API描述")
                .build();
    }

    public class CustRelativePathProvider extends AbstractPathProvider {
        public static final String ROOT = "/";

        @Override
        public String getOperationPath(String operationPath) {
            String suffix="";
            for(String s:keepJoyProperties.getUrlMappings()){
                if(s.startsWith("/*.")){
                    suffix=s.replace("/*.",".");
                    break;
                }
            }
            return operationPath+suffix;
        }

        @Override
        protected String applicationPath() {
            return ROOT;
        }

        @Override
        protected String getDocumentationPath() {
            return ROOT;
        }
    }
}
