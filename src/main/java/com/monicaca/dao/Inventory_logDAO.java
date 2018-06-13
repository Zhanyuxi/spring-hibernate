package com.monicaca.dao;

import com.monicaca.bean.Inventory;
import com.monicaca.bean.Inventory_log;
import com.monicaca.bean.Outbound_order;
import com.monicaca.bean.Storage_order;

public interface Inventory_logDAO extends BaseDAO {
    Inventory_log findByName(Inventory_log inventory_log);
    int count();
    void addByStorage(Storage_order storage_order, Inventory inventory);
    void addByOutbound(Outbound_order outbound_order,Inventory inventory);
}
