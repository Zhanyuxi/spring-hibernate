package com.monicaca.dao;

import com.monicaca.bean.Outbound_order;
public interface Outbound_orderDAO extends BaseDAO {
    Outbound_order findByName(Outbound_order outbound_order);
    int count();
}
