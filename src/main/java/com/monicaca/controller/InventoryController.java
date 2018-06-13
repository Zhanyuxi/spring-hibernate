package com.monicaca.controller;

import com.monicaca.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/inventory")
public class InventoryController {
    @Autowired
    InventoryService inventoryService;
    @Autowired
    HttpServletRequest request;

    @GetMapping("/")
    public String indexView(Model model){
        model.addAttribute("inventory",inventoryService.findAll());
        return "inventory";
    }
}
