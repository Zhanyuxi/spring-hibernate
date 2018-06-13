package com.monicaca.service;

import com.monicaca.bean.Outbound_order;
import com.monicaca.dao.Outbound_orderDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class Outbound_orderService {
    @Autowired
    private Outbound_orderDAO outbound_orderDAO;

    public Outbound_order findByOutbound_orderId(int outbound_orderId) {
        return outbound_orderDAO.findById(Outbound_order.class, outbound_orderId);
    }
    public Outbound_order findByProductName(Outbound_order outbound_order) {
        return outbound_orderDAO.findByName(outbound_order);
    }


    public List<Outbound_order> findAll() {
        return outbound_orderDAO.findAll(Outbound_order.class);
    }
    @Transactional
    public int count() {
        return outbound_orderDAO.count();
    }

    @Transactional
    public void createOutbound_order(Outbound_order outbound_order) {
        outbound_orderDAO.save(outbound_order);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW )

    public void updateOutbound_order(Outbound_order outbound_order) {
        outbound_orderDAO.update(outbound_order);
    }

    @Transactional
    public void deleteOutbound_order(int outbound_orderId) {
        outbound_orderDAO.deleteById(Outbound_order.class, outbound_orderId);
    }

    @Transactional
    public void deleteOutbound_order(Outbound_order outbound_order) {
        outbound_orderDAO.delete(outbound_order);
    }
}
