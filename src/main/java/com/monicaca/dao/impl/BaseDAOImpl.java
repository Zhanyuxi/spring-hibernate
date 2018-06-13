package com.monicaca.dao.impl;

import com.monicaca.dao.BaseDAO;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.HibernateTemplate;

import java.io.Serializable;
import java.util.List;

public class BaseDAOImpl implements BaseDAO{

    protected HibernateTemplate hibernateTemplate;
	
    public <T> T findById(Class<T> type, Serializable id) {
        return hibernateTemplate.get(type, id);  
    }  
  
    public <T> List<T> findAll(Class<T> type) {
        return hibernateTemplate.loadAll(type);  
    }  
  
    public void save(Object... entities) {
        for (Object entity : entities) {  
            hibernateTemplate.save(entity);  
        }  
    }  
  
    public void saveOrUpdate(Object entity) {
        hibernateTemplate.saveOrUpdate(entity);  
    }  
  
    public void update(Object... entities) {
        for (Object entity : entities) {  
            hibernateTemplate.update(entity);  
        }  
    }  
  
    public void delete(Object... entities) {
        for (Object entity : entities) {  
            if (entity != null) {  
                hibernateTemplate.delete(entity);  
            }  
        }  
    }  
  
    public void deleteById(Class<?> type, Serializable id) {
        if (id == null) {  
            return;  
        }  
        Object entity = findById(type, id);  
        if (entity == null) {  
            return;  
        }  
        delete(entity);  
    }  
  
    public void refresh(Object... entities) {
        for (Object entity : entities) {  
            hibernateTemplate.refresh(entity);  
        }  
    }  
  
    public void flush() {
        hibernateTemplate.flush();  
    }

	public <T> List<T> findBySql(final String sql, final Object[] obj) {
		return  hibernateTemplate.execute(new HibernateCallback<List<T>>() {
			@SuppressWarnings("unchecked")
			public List<T> doInHibernate(Session session) throws HibernateException {
				SQLQuery sqlQuery = session.createSQLQuery(sql);
			 	if(obj != null && obj.length > 0){
			 		for(int i = 0;i < obj.length; i++){
			 			//与JDBC不同的是，此处参数的索引是以0开始，而JDBC的PreparedStatement设置参数的索引是以1开始  
			 			sqlQuery.setParameter(i, obj[i]);  
			 		}  
			 	}  
				return sqlQuery.list();
			}
			
		});
	}  
	
}
