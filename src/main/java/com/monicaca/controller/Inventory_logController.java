package com.monicaca.controller;

import com.monicaca.service.Inventory_logService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/inventory_log")
public class Inventory_logController {
    @Autowired
    Inventory_logService inventory_logService;
    @Autowired
    HttpServletRequest request;

    @GetMapping("/")
    public String indexView(Model model){
        model.addAttribute("inventory_log",inventory_logService.findAll());
        return "inventory_log";
    }
}
