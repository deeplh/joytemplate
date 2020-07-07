package com.keepjoy.core.configuration;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.apache.commons.lang.StringUtils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
public class DruidConfiguration {
    @Value("${druid.allow}")
    private String allow;

    @Value("${druid.deny}")
    private String deny;

    @Value("${druid.exclusions}")
    private String exclusions;

    @Value("${druid.useGlobalDataSourceStat}")
    private Boolean useGlobalDataSourceStat;

    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;

    @Value(value = "${spring.datasource.initial-size}")
    private int initialSize;

    @Value(value = "${spring.datasource.min-idle}")
    private int minIdle;

    @Value(value = "${spring.datasource.max-active}")
    private int maxActive;

    @Value(value = "${spring.datasource.max-wait}")
    private int maxWait;


    @Bean(name="defaultDruidDataSource")
    public DataSource druidDataSource() throws SQLException {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUsername(username);
        druidDataSource.setPassword(password);
        druidDataSource.setUrl(dbUrl);
        druidDataSource.setFilters("stat,wall");
        druidDataSource.setInitialSize(initialSize);
        druidDataSource.setMinIdle(minIdle);
        druidDataSource.setMaxActive(maxActive);
        druidDataSource.setMaxWait(maxWait);
        druidDataSource.setUseGlobalDataSourceStat(useGlobalDataSourceStat);
        druidDataSource.setDriverClassName(driverClassName);
        return druidDataSource;
    }


    @Bean
    public ServletRegistrationBean druidServlet() {

        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        servletRegistrationBean.setName("druidServlet");
        // IP白名单
        if(StringUtils.isNotEmpty(allow))servletRegistrationBean.addInitParameter("allow", allow);
        // IP黑名单(共同存在时，deny优先于allow)
        if(StringUtils.isNotEmpty(deny))servletRegistrationBean.addInitParameter("deny", deny);
        //控制台管理用户
        servletRegistrationBean.addInitParameter("loginUsername", "admin");
        servletRegistrationBean.addInitParameter("loginPassword", "admin");
        //是否能够重置数据 禁用HTML页面上的“Reset All”功能
        servletRegistrationBean.addInitParameter("resetEnable", "false");
        return servletRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        String exs="*.js,*.gif,*.jpg,*.png,*.css,*.ico,*.mp4,/druid/*";
        if(StringUtils.isNotEmpty(exclusions)){
            exs=exclusions;
        }
        filterRegistrationBean.addInitParameter("exclusions", exs);
        return filterRegistrationBean;
    }

}
