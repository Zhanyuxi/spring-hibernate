package com.monicaca.dao.impl;

import com.monicaca.bean.Outbound_order;
import com.monicaca.dao.Outbound_orderDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class Outbound_orderDAOImpl extends BaseDAOImpl implements Outbound_orderDAO {
    @Autowired
    public void setHibernateTemplate(HibernateTemplate hibernateTemplate){
        super.hibernateTemplate = hibernateTemplate;
    }

    public Outbound_order findByName(Outbound_order outbound_order) {
        return  hibernateTemplate.findByExample(outbound_order).get(0);
    }
    public  int count(){
        String sql = "select count(*) from Outbound_order";
        return DataAccessUtils.intResult(hibernateTemplate.find(sql));
    }
}
