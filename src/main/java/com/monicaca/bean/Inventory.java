package com.monicaca.bean;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table
public class Inventory implements Serializable{
    private static final long serialVersionUID = 8340678862555066263L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "product_name")
    private String productName;
    @Column(name = "inventory")
    private Integer inventory;
    @Column(name = "max_inventory")
    private Integer maxInventory;
    @Column(name = "unit")
    private String unit;

    public Inventory(String productName) {this.productName=productName; }

    public Inventory() { }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getInventory() {
        return inventory;
    }

    public void setInventory(Integer inventory) {
        this.inventory = inventory;
    }

    public Integer getMaxInventory() {
        return maxInventory;
    }

    public void setMaxInventory(Integer maxInventory) {
        this.maxInventory = maxInventory;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
