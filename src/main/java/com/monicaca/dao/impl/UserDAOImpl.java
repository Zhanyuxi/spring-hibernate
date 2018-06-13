package com.monicaca.dao.impl;

import com.monicaca.bean.User;
import com.monicaca.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAOImpl extends BaseDAOImpl implements UserDAO{
	@Autowired
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate){
		super.hibernateTemplate = hibernateTemplate;
	}

	public User findByName(User user) {
		return  hibernateTemplate.findByExample(user).get(0);
	}
	public  int count(){
		String sql = "select count(*) from User";
		return DataAccessUtils.intResult(hibernateTemplate.find(sql));
	}

}
