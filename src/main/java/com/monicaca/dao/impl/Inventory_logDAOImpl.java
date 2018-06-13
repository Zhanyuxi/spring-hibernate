package com.monicaca.dao.impl;

import com.monicaca.bean.Inventory;
import com.monicaca.bean.Inventory_log;
import com.monicaca.bean.Outbound_order;
import com.monicaca.bean.Storage_order;
import com.monicaca.dao.Inventory_logDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public class Inventory_logDAOImpl extends BaseDAOImpl implements Inventory_logDAO {
    @Autowired
    public void setHibernateTemplate(HibernateTemplate hibernateTemplate){
        super.hibernateTemplate = hibernateTemplate;
    }

    public Inventory_log findByName(Inventory_log inventory_log){
        return hibernateTemplate.findByExample(inventory_log).get(0);
    }

    public  int count(){
        String sql = "select count(*) from Inventory_log";
        return DataAccessUtils.intResult(hibernateTemplate.find(sql));
    }
    public void addByStorage(Storage_order storage_order, Inventory inventory){
        Inventory_log inventory_log=new Inventory_log();
        inventory_log.setAction("1");
        inventory_log.setCreated(new Date());
        inventory_log.setAmount(storage_order.getAmount());
        inventory_log.setInventoryId(storage_order.getId());
        hibernateTemplate.save(inventory_log);
    }
    public void addByOutbound(Outbound_order outbound_order,Inventory inventory){
        Inventory_log inventory_log=new Inventory_log();
        inventory_log.setInventoryId(outbound_order.getInventoryId());
        inventory_log.setAction("2");
        inventory_log.setAmount(outbound_order.getAmount());
        inventory_log.setCreated(new Date());
        hibernateTemplate.save(inventory_log);
    }
}
