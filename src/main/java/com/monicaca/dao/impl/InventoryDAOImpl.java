package com.monicaca.dao.impl;

import com.monicaca.bean.Inventory;
import com.monicaca.bean.Outbound_order;
import com.monicaca.bean.Storage_order;
import com.monicaca.dao.InventoryDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class InventoryDAOImpl extends BaseDAOImpl implements InventoryDAO {
    @Autowired
    public void setHibernateTemplate(HibernateTemplate hibernateTemplate){
        super.hibernateTemplate = hibernateTemplate;
    }

    public Inventory findByName(Inventory inventory){
        return hibernateTemplate.findByExample(inventory).get(0);
    }

    public  int count(){
        String sql = "select count(*) from Inventory";
        return DataAccessUtils.intResult(hibernateTemplate.find(sql));
    }
    public Inventory addInventory(Storage_order storage_order){
        Inventory inventory;
        try{
            inventory=findByName(new Inventory(storage_order.getProductName()));
            inventory.setInventory(storage_order.getAmount()+inventory.getInventory());
            hibernateTemplate.update(inventory);
        }catch(Exception e){
            inventory=new Inventory();
            inventory.setMaxInventory(1000);
            inventory.setInventory(storage_order.getAmount());
            inventory.setUnit(storage_order.getUnit());
            inventory.setProductName(storage_order.getProductName());
            hibernateTemplate.save(inventory);
            return inventory;
        }
        return inventory;
    }

    public Inventory deleteInventory(Outbound_order outbound_order) {
        Inventory inventory;
        try{
            inventory=findByName(new Inventory(outbound_order.getInventoryName()));
            inventory.setInventory(inventory.getInventory()-outbound_order.getAmount());
            hibernateTemplate.update(inventory);
        }catch(Exception e){
            inventory=new Inventory();
            inventory.setMaxInventory(1000);
            inventory.setInventory(outbound_order.getAmount());
            inventory.setUnit(outbound_order.getUnit());
            inventory.setProductName(outbound_order.getInventoryName());
            hibernateTemplate.save(inventory);
            return inventory;
        }
        return inventory;
    }
    public Inventory cancleByStorage(Storage_order storage_order){
        Inventory inventory;
        inventory=findByName(new Inventory(storage_order.getProductName()));
        inventory.setInventory(inventory.getInventory()-storage_order.getAmount());
        hibernateTemplate.update(inventory);
        return inventory;
    }

    public Inventory cancleByOutbound(Outbound_order outbound_order) {
        Inventory inventory;
        inventory=findByName(new Inventory(outbound_order.getInventoryName()));
        inventory.setInventory(inventory.getInventory()+outbound_order.getAmount());
        hibernateTemplate.update(inventory);
        return inventory;
    }
}
