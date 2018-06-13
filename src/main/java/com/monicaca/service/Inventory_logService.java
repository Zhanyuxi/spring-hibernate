package com.monicaca.service;

import com.monicaca.bean.Inventory;
import com.monicaca.bean.Inventory_log;
import com.monicaca.bean.Outbound_order;
import com.monicaca.bean.Storage_order;
import com.monicaca.dao.Inventory_logDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class Inventory_logService {
    @Autowired
    private Inventory_logDAO inventory_logDAO;
    public Inventory_log findByInventory_logId(int inventory_logId){
        return inventory_logDAO.findById(Inventory_log.class,inventory_logId);
    }

    public Inventory_log findByProductName(Inventory_log inventory_log){
        return inventory_logDAO.findByName(inventory_log);
    }

    public List<Inventory_log> findAll(){
        return inventory_logDAO.findAll(Inventory_log.class);
    }
    @Transactional
    public int count() {
        return inventory_logDAO.count();
    }

    @Transactional
    public void deleteInventory_log(int inventory_logId) {
        inventory_logDAO.deleteById(Inventory_log.class, inventory_logId);
    }

    @Transactional
    public void deleteInventory_log(Inventory_log inventory_log) {
        inventory_logDAO.delete(inventory_log);
    }
    @Transactional
    public void createInventory_log(Inventory_log inventory_log) {
        inventory_logDAO.save(inventory_log);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW )

    public void updateInventory_log(Inventory_log inventory_log) {
        inventory_logDAO.update(inventory_log);
    }
    @Transactional
    public void addByStorage(Storage_order storage_order, Inventory inventory){
        inventory_logDAO.addByStorage(storage_order,inventory);
    }
    @Transactional
    public void addByOutbound(Outbound_order outbound_order, Inventory inventory){
        inventory_logDAO.addByOutbound(outbound_order,inventory);
    }
}
