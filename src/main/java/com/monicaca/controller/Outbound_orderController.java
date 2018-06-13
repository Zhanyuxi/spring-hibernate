package com.monicaca.controller;

import com.monicaca.bean.Inventory;
import com.monicaca.bean.Outbound_order;
import com.monicaca.service.InventoryService;
import com.monicaca.service.Inventory_logService;
import com.monicaca.service.Outbound_orderService;
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
@RequestMapping("/outbound_order")
public class Outbound_orderController {
    @Autowired
    Outbound_orderService outbound_orderService;
    @Autowired
    InventoryService inventoryService;
    @Autowired
    Inventory_logService inventory_logService;
    @Autowired
    HttpServletRequest request;

    @GetMapping("/")
    public String indexView(
            Model model){
        model.addAttribute("outbound_order",outbound_orderService.findAll());
        return "outbound_order";
    }
    @GetMapping("/add")
    public String addView(
            Model model){
        return "outbound_orderadd";
    }
    @GetMapping("/change/{id}")
    public String changeView(
            Model model, @PathVariable int id){
        model.addAttribute("id",id);
        return "outbound_orderchange";
    }
    @GetMapping("/delete/{id}")
    public String deleteView(
            Model model, @PathVariable int id){

        outbound_orderService.deleteOutbound_order(id);
        model.addAttribute("msg","删除成功");
        model.addAttribute("outbound_order",outbound_orderService.findAll());
        return "outbound_order";
    }
    @PostMapping("/update/{id}")
    public String updateView(
            Model model, @PathVariable int id,Outbound_order outbound_order){
        Outbound_order realoutbound_order=outbound_orderService.findByOutbound_orderId(id);
        realoutbound_order.setAmount(outbound_order.getAmount());
        outbound_orderService.updateOutbound_order(realoutbound_order);
        model.addAttribute("msg","修改成功");
        model.addAttribute("outbound_order",outbound_orderService.findAll());
        return "outbound_order";
    }
    @PostMapping("/save")
    public String updateView(Model model, Outbound_order outbound_order) {
        Inventory inventory;
        try{
            inventory=inventoryService.findByInventoryId(outbound_order.getInventoryId());
            if(outbound_order.getAmount()<=inventory.getInventory()){
                outbound_order.setCreated(new Date());
                outbound_order.setStatus("1");
                outbound_orderService.createOutbound_order(outbound_order);
                model.addAttribute("msg", "新增成功");
                model.addAttribute("outbound_order", outbound_orderService.findAll());
            }
            else {
                model.addAttribute("msg","出库失败，超过现有库存");
                model.addAttribute("outbound_order",outbound_orderService.findAll());
            }
        }catch (Exception e){
            model.addAttribute("msg","出库失败，库存中无该商品");
            model.addAttribute("outbound_order",outbound_orderService.findAll());
        }
        return "outbound_order";
    }
    @GetMapping("/check/{id}")
    public String checkView(Model model,@PathVariable int id){
        Outbound_order outbound_order=outbound_orderService.findByOutbound_orderId(id);
        Inventory inventory;
        try{
            inventory=inventoryService.findByProductName(new Inventory(outbound_order.getInventoryName()));
            if(outbound_order.getAmount()>inventory.getInventory()){
                model.addAttribute("msg","无法审核，超过库存数量");
                model.addAttribute("outbound_order",outbound_orderService.findAll());
            }
            else{
                inventory=inventoryService.deleteInventory(outbound_order);
                outbound_order.setStatus("2");
                outbound_orderService.updateOutbound_order(outbound_order);
                inventory_logService.addByOutbound(outbound_order,inventory);
                model.addAttribute("msg","审核成功");
                model.addAttribute("outbound_order",outbound_orderService.findAll());
            }
        }catch (Exception e){
            inventory=inventoryService.deleteInventory(outbound_order);
            outbound_order.setStatus("2");
            outbound_orderService.updateOutbound_order(outbound_order);
            inventory_logService.addByOutbound(outbound_order,inventory);
            model.addAttribute("msg","审核成功");
            model.addAttribute("outbound_order",outbound_orderService.findAll());
            return "outbound_order";
        }
        return "outbound_order";
    }
    @GetMapping("/cancle/{id}")
    public String cancleView(Model model,@PathVariable int id){
        Outbound_order outbound_order=outbound_orderService.findByOutbound_orderId(id);
        Inventory inventory=inventoryService.cancleByOutbound(outbound_order);
        outbound_order.setStatus("1");
        outbound_orderService.updateOutbound_order(outbound_order);
        model.addAttribute("msg","取消审核成功");
        model.addAttribute("outbound_order",outbound_orderService.findAll());
        return "outbound_order";
    }
}
