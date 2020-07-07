package com.keepjoy.core.configuration;

import com.keepjoy.core.properties.KeepJoyProperties;
import com.keepjoy.security.properties.SecurityProperties;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.SessionFactory;
import org.hibernate.jpa.HibernateEntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "defaultEntityManagerFactory",
        transactionManagerRef = "defaultHibernateTransactionManager")
public class HibernateConfiguration {

    @Autowired
    @Qualifier("defaultDruidDataSource")
    private DataSource defaultDruidDataSource;

    @Autowired
    private JpaProperties jpaProperties;

    @Value("${spring.hibernate.packagesToScan}")
    private String packagesToScan;

    @Value("${keepjoy.security.securityUserClassName}")
    private String useSecurityFlag;

    @Value("${keepjoy.weixin.appId}")
    private String useWeixinFlag;



    @Bean(name="defaultEntityManagerFactory")
    public EntityManagerFactory entityManagerFactory() {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setDataSource(defaultDruidDataSource);// 数据源
        List<String> scans=new ArrayList<String>();
        scans.add("com.keepjoy.core.entity");
        if(StringUtils.isNotEmpty(useSecurityFlag)){
            scans.add("com.keepjoy.security.entity");
        }
        if(StringUtils.isNotEmpty(useWeixinFlag)){
            scans.add("com.keepjoy.weixin.entity");
        }
        if(StringUtils.isNotEmpty(packagesToScan)){
            scans.add(packagesToScan);
        }
        String[] s = new String[scans.size()];
        factory.setPackagesToScan(scans.toArray(s));
        factory.setJpaPropertyMap(jpaProperties.getProperties());
        factory.afterPropertiesSet();// 在完成了其它所有相关的配置加载以及属性设置后,才初始化
        return factory.getObject();
    }

    @Bean(name="defaultSessionFactory")
    public SessionFactory sessionFactory(@Qualifier("defaultEntityManagerFactory")EntityManagerFactory emf) {
        SessionFactory sessionFactory = emf.unwrap(SessionFactory.class);
        return sessionFactory;
    }

    /**
          * 这种方式使用hibernate后,默认的事务管理器会失效,所以这个bean为必填项
          * @param emf
          * @return
          */
    @Bean(name="defaultHibernateTransactionManager")
    public HibernateTransactionManager transcationManager(@Qualifier("defaultEntityManagerFactory")EntityManagerFactory emf) {
        return new HibernateTransactionManager(sessionFactory(emf));
    }


}
