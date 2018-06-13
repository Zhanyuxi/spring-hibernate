package com.monicaca.dao;

import com.monicaca.bean.Inventory;
import com.monicaca.bean.Outbound_order;
import com.monicaca.bean.Storage_order;

public interface InventoryDAO extends BaseDAO{
    Inventory findByName(Inventory inventory);
    int count();
    Inventory addInventory(Storage_order storage_order);
    Inventory deleteInventory(Outbound_order outbound_order);
    Inventory cancleByStorage(Storage_order storage_order);
    Inventory cancleByOutbound(Outbound_order outbound_order);
}
