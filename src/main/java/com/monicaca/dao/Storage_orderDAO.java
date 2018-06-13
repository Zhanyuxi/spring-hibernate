package com.monicaca.dao;

import com.monicaca.bean.Storage_order;

public interface Storage_orderDAO extends BaseDAO{

    Storage_order findByName(Storage_order storage_order);
    int count();
}
