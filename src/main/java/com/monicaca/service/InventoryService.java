package com.monicaca.service;

import com.monicaca.bean.Inventory;
import com.monicaca.bean.Outbound_order;
import com.monicaca.bean.Storage_order;
import com.monicaca.dao.InventoryDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InventoryService {
    @Autowired
    private InventoryDAO inventoryDAO;

    public Inventory findByInventoryId(int inventoryId){
        return inventoryDAO.findById(Inventory.class,inventoryId);
    }

    public Inventory findByProductName(Inventory inventory){
        return inventoryDAO.findByName(inventory);
    }

    public List<Inventory> findAll(){
        return inventoryDAO.findAll(Inventory.class);
    }

    @Transactional
    public int count() {
        return inventoryDAO.count();
    }
    @Transactional
    public void createInventory(Inventory inventory) {
        inventoryDAO.save(inventory);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW )

    public void updateInventory(Inventory inventory) {
        inventoryDAO.update(inventory);
    }

    @Transactional
    public void deleteInventory(int inventoryId) {
        inventoryDAO.deleteById(Inventory.class, inventoryId);
    }

    @Transactional
    public void deleteInventory(Inventory inventory) {
        inventoryDAO.delete(inventory);
    }
    @Transactional
    public Inventory addInventory(Storage_order storage_order) {
        return inventoryDAO.addInventory(storage_order);
    }
    @Transactional
    public Inventory deleteInventory(Outbound_order outbound_order){
        return inventoryDAO.deleteInventory(outbound_order);
    }
    @Transactional
    public Inventory cancleByStorage(Storage_order storage_order){
        return inventoryDAO.cancleByStorage(storage_order);
    }
    @Transactional
    public Inventory cancleByOutbound(Outbound_order outbound_order){
        return inventoryDAO.cancleByOutbound(outbound_order);
    }
}
