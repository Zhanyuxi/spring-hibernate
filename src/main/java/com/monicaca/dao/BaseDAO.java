package com.monicaca.dao;

import java.io.Serializable;
import java.util.List;

public interface BaseDAO {
	<T> List<T> findBySql(String sql, Object[] obj);
	
	<T> T findById(Class<T> type, Serializable id);  
	  
    <T> List<T> findAll(Class<T> type);  
  
    void save(Object... entities);  
  
    void update(Object... entities);  
  
    void saveOrUpdate(Object entity);  
  
    void delete(Object... entities);  
  
    void deleteById(Class<?> type, Serializable id);  
  
    void refresh(Object... entities);  
  
    void flush(); 
}
