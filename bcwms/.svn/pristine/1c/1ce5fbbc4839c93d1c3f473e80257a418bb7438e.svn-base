package com.keepjoy.core.dao;

import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;


@SuppressWarnings("ALL")
@Repository("keepJoyDaoImpl")
public class KeepJoyDaoImpl extends HibernateDaoSupport {


    @Autowired
    @Qualifier("defaultJdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    public void setSessionFactoryOverride(SessionFactory sessionFactory)
    {
        super.setSessionFactory(sessionFactory);
    }


    /**********公用的简单的方法**************/
    public void save(Object obj){
        super.getHibernateTemplate().save(obj);
    }

    public void update(Object obj){
        super.getHibernateTemplate().update(obj);
    }

    public void update(Object obj,LockMode lockMode){
        super.getHibernateTemplate().update(obj,lockMode);
    }

    public void saveOrUpdate(Object obj){
        super.getHibernateTemplate().saveOrUpdate(obj);
    }

    public void remove(Object obj){
        super.getHibernateTemplate().delete(obj);
    }

    public void remove(Object obj,LockMode lockMode){
        super.getHibernateTemplate().delete(obj,lockMode);
    }

    public void evict(Object obj){
        super.getHibernateTemplate().evict(obj);
    }

    public void evict(List objs){
        for(Object obj:objs){
            super.getHibernateTemplate().evict(obj);
        }
    }

    public List find(String hql){
        return super.getHibernateTemplate().find(hql);
    }

    public List find(String hql,Object value){
        return super.getHibernateTemplate().find(hql,value);
    }

    public List find(String hql,Object...values){
        return super.getHibernateTemplate().find(hql,values);
    }

    public List find(String hql,LockMode lockMode){
        return super.getHibernateTemplate().find(hql,lockMode);
    }

    public List find(String hql,Object value,LockMode lockMode){
        return super.getHibernateTemplate().find(hql,value,lockMode);
    }

    public List find(String hql,LockMode lockMode,Object...values){
        return super.getHibernateTemplate().find(hql,lockMode,values);
    }

    public <T> T get(Class<T> entityClass,Serializable id) {
        return super.getHibernateTemplate().get(entityClass, id);
    }

    public Object get(String entityName,Serializable id) {
        return super.getHibernateTemplate().get(entityName, id);
    }

    public <T> T get(Class<T> entityClass,Serializable id,LockMode lockMode) {
        return super.getHibernateTemplate().get(entityClass, id,lockMode);
    }

    public Session getCurrentSession(){
        return super.getHibernateTemplate().execute(new HibernateCallback<Session>() {
            public Session doInHibernate(Session s) throws HibernateException {
                return s;
            }
        });
    }


    @SuppressWarnings("unchecked")
    public <T> T findObjectFromListByHql(Class<T> newType,String hql,Object...values) {
        List list=null;
        if(null!=values && values.length>0){
            list=super.getHibernateTemplate().find(hql,values);
        }else{
            list=super.getHibernateTemplate().find(hql);
        }
        if(null==list||list.size()!=1)return null;
        else {
            return (T) list.get(0);
        }
    }

    public void bulkUpdate(String sql, Object... vals){
        super.getHibernateTemplate().bulkUpdate(sql,vals);
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public HibernateTemplate getHt(){
        return super.getHibernateTemplate();
    }

    public RedisTemplate getRedisTemplate() {
        return redisTemplate;
    }
}
