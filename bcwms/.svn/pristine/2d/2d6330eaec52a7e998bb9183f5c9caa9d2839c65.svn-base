package com.keepjoy.core.context;

import com.keepjoy.core.dao.KeepJoyDaoImpl;
import com.keepjoy.core.util.AopTargetUtil;
import org.springframework.beans.BeansException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate5.HibernateTemplate;


public class KeepJoySpringContext {


    public static KeepJoyDaoImpl getKeepJoyDao(){
        return ApplicationContextProvider.getApplicationContext().getBean(KeepJoyDaoImpl.class);
    }

    public static JdbcTemplate getJdbcTemplate(){
        return getKeepJoyDao().getJdbcTemplate();
    }

    public static HibernateTemplate getHibernateTemplate(){
        return  getKeepJoyDao().getHt();
    }

    public static ApplicationContext getSpringContext() {
        return ApplicationContextProvider.getApplicationContext();
    }

    public static <T> T getSourceBeanFromSpringContext(Class<T> beanClass) throws BeansException, Exception{
        Object bean=AopTargetUtil.getTarget(ApplicationContextProvider.getApplicationContext().getBean(beanClass.getName()));
        return (T) bean;
    }
}
