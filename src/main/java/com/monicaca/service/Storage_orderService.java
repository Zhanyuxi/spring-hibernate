package com.monicaca.service;

import com.monicaca.bean.Storage_order;
import com.monicaca.dao.InventoryDAO;
import com.monicaca.dao.Storage_orderDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class Storage_orderService {
    @Autowired
    private Storage_orderDAO storage_orderDAO;
    private InventoryDAO inventoryDAO;

    public Storage_order findByStorage_orderId(int storage_orderId) {
        return storage_orderDAO.findById(Storage_order.class, storage_orderId);
    }

    public Storage_order findByProductName(Storage_order storage_order) {
        return storage_orderDAO.findByName(storage_order);
    }


    public List<Storage_order> findAll() {
        return storage_orderDAO.findAll(Storage_order.class);
    }

    @Transactional
    public int count() {
        return storage_orderDAO.count();
    }

    @Transactional
    public void createStorage_order(Storage_order storage_order) {
        storage_orderDAO.save(storage_order);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)

    public void updateStorage_order(Storage_order storage_order) {
        storage_orderDAO.update(storage_order);
    }

    @Transactional
    public void deleteStorage_order(int storage_orderId) {
        storage_orderDAO.deleteById(Storage_order.class, storage_orderId);
    }

    @Transactional
    public void deleteStorage_order(Storage_order storage_order) {
        storage_orderDAO.delete(storage_order);
    }


}
