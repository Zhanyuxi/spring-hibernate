package com.monicaca.controller;

import com.monicaca.bean.Storage_order;
import com.monicaca.bean.Inventory;
import com.monicaca.service.InventoryService;
import com.monicaca.service.Inventory_logService;
import com.monicaca.service.Storage_orderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
@RequestMapping("/storage_order")
public class Storage_orderController {
    @Autowired
    Storage_orderService storage_orderService;
    @Autowired
    InventoryService inventoryService;
    @Autowired
    Inventory_logService inventory_logService;
    @Autowired
    HttpServletRequest request;

    @GetMapping("/")
    public String indexView(
            Model model){
        model.addAttribute("storage_order",storage_orderService.findAll());
        return "storage_order";
    }
    @GetMapping("/add")
    public String addView(
            Model model){
        return "storage_orderadd";
    }
    @GetMapping("/change/{id}")
    public String changeView(
            Model model, @PathVariable int id){
        model.addAttribute("id",id);
        return "storage_orderchange";
    }
    @GetMapping("/delete/{id}")
    public String deleteView(
            Model model, @PathVariable int id){
        Storage_order storage_order=storage_orderService.findByStorage_orderId(id);
        storage_orderService.deleteStorage_order(id);
        model.addAttribute("msg", "删除成功");
        model.addAttribute("storage_order", storage_orderService.findAll());
        return "storage_order";
    }
    @PostMapping("/update/{id}")
    public String updateView(
            Model model, @PathVariable int id,Storage_order storage_order){
        Storage_order realstorage_order=storage_orderService.findByStorage_orderId(id);
        realstorage_order.setAmount(storage_order.getAmount());
        storage_orderService.updateStorage_order(realstorage_order);
        model.addAttribute("msg", "修改成功");
        model.addAttribute("storage_order", storage_orderService.findAll());
        return "storage_order";
    }
    @PostMapping("/save")
    public String updateView(
            Model model, Storage_order storage_order){
        storage_order.setCreated(new Date());
        storage_order.setStatus("1");
        storage_orderService.createStorage_order(storage_order);
        model.addAttribute("msg","新增成功");
        model.addAttribute("storage_order",storage_orderService.findAll());
        return "storage_order";
    }
    @GetMapping("/check/{id}")
    public String checkView(Model model,@PathVariable int id){
        Storage_order storage_order=storage_orderService.findByStorage_orderId(id);
        Inventory inventory;
        try{
            inventory=inventoryService.findByProductName(new Inventory(storage_order.getProductName()));
            if((inventory.getInventory()+storage_order.getAmount())>inventory.getMaxInventory()){
                model.addAttribute("msg","无法审核，超过最大库存");
                model.addAttribute("storage_order",storage_orderService.findAll());
            }
            else{
                inventory=inventoryService.addInventory(storage_order);
                storage_order.setStatus("2");
                storage_orderService.updateStorage_order(storage_order);
                inventory_logService.addByStorage(storage_order,inventory);
                model.addAttribute("msg","审核成功");
                model.addAttribute("storage_order", storage_orderService.findAll());
            }
        }catch (Exception e){
            inventory=inventoryService.addInventory(storage_order);
            storage_order.setStatus("2");
            storage_orderService.updateStorage_order(storage_order);
            inventory_logService.addByStorage(storage_order,inventory);
            model.addAttribute("msg","审核成功");
            model.addAttribute("storage_order", storage_orderService.findAll());
            return "storage_order";
        }
        return "storage_order";
    }
    @GetMapping("/cancle/{id}")
    public String cancleView(Model model,@PathVariable int id){
        Storage_order storage_order=storage_orderService.findByStorage_orderId(id);
        Inventory inventory=inventoryService.cancleByStorage(storage_order);
        storage_order.setStatus("1");
        storage_orderService.updateStorage_order(storage_order);
        model.addAttribute("msg","取消审核成功");
        model.addAttribute("storage_order",storage_orderService.findAll());
        return "storage_order";
    }
}
