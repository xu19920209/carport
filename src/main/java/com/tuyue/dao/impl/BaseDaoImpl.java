package com.tuyue.dao.impl;

/**
 * Created by Administrator on 2017/6/30.
 */

import com.tuyue.dao.IBaseDao;
import com.tuyue.util.Page;
import com.tuyue.util.Parameter;
import org.hibernate.*;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Repository
public class BaseDaoImpl<T> implements IBaseDao<T> {

    private int BATCH_MAX_ROW = 5;

    private SessionFactory sessionFactory;
    private Class<T> entityClass;

    //construct methods
    public BaseDaoImpl(){
    }

    public BaseDaoImpl(Class<T> entityClass){
        this.entityClass = entityClass;
    }


    @Override
    public int save(T entity)  {
        Session session = this.getSession();
        session.beginTransaction();
        try {
            Serializable save = session.save(entity);
            session.getTransaction().commit();

            return (Integer)save;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int batchSave(List<T> list) throws Exception {
        Session session = this.getSession();
        session.beginTransaction();
        for (int i = 0; i < list.size(); ++i) {
            session.save(list.get(i));
            if (i % BATCH_MAX_ROW == 0) {
                session.flush();
                session.clear();
            }
        }
        session.flush();
        session.clear();
        session.getTransaction().commit();
        return list.size();
    }


    @Override
    public boolean deleteAll(Collection entities) {
        try {
            Session session = this.getSession();
            session.beginTransaction();
            for (Object entity : entities) {
                session.delete(entity);
            }
            session.getTransaction().commit();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(T entity) throws Exception {
        Session session = this.getSession();
        session.beginTransaction();
        try {
            session.delete(entity);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public int deleteWithHql(String hql){
        Session session = this.getSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery(hql);
            int i = query.executeUpdate();
            session.getTransaction().commit();
            return i;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public boolean update(T entity) {
        Session session = this.getSession();
        session.beginTransaction();
        try {
            session.update(entity);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            session.close();
            e.printStackTrace();
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    public T getOne(Class<T> clazz,int id) throws Exception {
        Session session = this.getSession();
        session.beginTransaction();
        Object returnObject = session.get(clazz,id);
        session.getTransaction().commit();
        return (T) returnObject;
    }

    @Override
    public T findOne(String hql) throws Exception {
        return findOne(hql,null);
    }

    @SuppressWarnings("unchecked")
    @Override
    public T findOne(final String hql, final Parameter parameter) throws Exception {
        Session session = this.getSession();
        session.beginTransaction();
        Query query = session.createQuery(hql);
        setParameter(query, parameter);
        Object returnObject = query.setMaxResults(1).uniqueResult();
        session.getTransaction().commit();
//        session.close();
        return (T) returnObject;
    }

    @Override
    public List<T> findList(final String hql) throws Exception {
        return findList(hql, null);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> findList(final String hql, final Parameter parameter) throws Exception {
        Session session = this.getSession();
        session.beginTransaction();
        Query query = session.createQuery(hql);
        setParameter(query, parameter);
        List<T> returnList = query.list();
        session.getTransaction().commit();
        return returnList;
    }

    /**
     *
     * @param query
     * @param parameter
     * set sql parameters
     */
    private void setParameter(Query query, Parameter parameter) {
        if (parameter != null) {
            Set<String> keySet = parameter.keySet();
            for (String string : keySet) {
                Object value = parameter.get(string);
                if (value instanceof Collection<?>) {
                    query.setParameterList(string, (Collection<?>) value);
                } else if (value instanceof Object[]) {
                    query.setParameterList(string, (Object[]) value);
                } else {
                    query.setParameter(string, value);
                }
            }
        }
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Resource(name="sessionFactory")//在applicationContext.xml文件中有配置
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * @return session
     */
    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @SuppressWarnings("unchecked")
    @Override
    public Page<T> findPage(Integer currentPage, Integer pageSize, String queryHql, String countHql, Object[] values)
            throws HibernateException, SQLException {
        pageSize = pageSize == null ? 0 : pageSize;
        currentPage = currentPage == null ? 0 : currentPage;
        Session session = this.getSession();
        session.beginTransaction();
        Query query = session.createQuery(queryHql);
        if (values != null) {
            for (int i = 0; i < values.length; i++) {
                query.setParameter(i, values[i]);
            }
        }
        // 如果pageSize<=0则查询全部，用于打印导出等...
        if (pageSize > 0) {
                query.setFirstResult(pageSize * (currentPage - 1));//设置要查询的结果集的开始索引位置
                query.setMaxResults(pageSize);//设置要查询的结果集的数量
            }

        List<T> returnList = query.list();
        session.getTransaction().commit();
        long totalRecords = findCount(countHql, values);
        if (currentPage==0||pageSize==0){
            return new Page<T>(returnList,0,0);
        }
        return new Page<T>(returnList, totalRecords, currentPage, pageSize);
    }
    @Override
    public Page<T> findPage(Integer currentPage, Integer pageSize, String queryHql, String countHql)
            throws HibernateException, SQLException {
        return findPage(currentPage, pageSize, queryHql, countHql, null);
    }

    @Override
    public long findCount(String hql) {
        return findCount(hql, null);
    }

    @Override
    public long findCount(String hql, Object[] values) {
        Session session = this.getSession();
        session.beginTransaction();
        Query query = session.createQuery(hql);
        if (values != null) {
            for (int i = 0; i < values.length; i++) {
                query.setParameter(i, values[i]);
            }
        }
        Long returnLong = (Long) query.setMaxResults(1).uniqueResult();
        session.getTransaction().commit();
        return returnLong;
    }
    public int updateandsave(String hql){
        StatelessSession session = sessionFactory.openStatelessSession();
        SQLQuery sqlQuery = session.createSQLQuery(hql);

        return 0;
    }
}
