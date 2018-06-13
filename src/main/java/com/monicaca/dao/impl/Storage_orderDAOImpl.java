package com.monicaca.dao.impl;

import com.monicaca.bean.Storage_order;
import com.monicaca.dao.Storage_orderDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class Storage_orderDAOImpl extends BaseDAOImpl implements Storage_orderDAO{
    @Autowired
    public void setHibernateTemplate(HibernateTemplate hibernateTemplate){
        super.hibernateTemplate = hibernateTemplate;
    }

    public Storage_order findByName(Storage_order storage_order) {
        return  hibernateTemplate.findByExample(storage_order).get(0);
    }
    public  int count(){
        String sql = "select count(*) from Storage_order";
        return DataAccessUtils.intResult(hibernateTemplate.find(sql));
    }
}
