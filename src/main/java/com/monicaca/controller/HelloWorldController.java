package com.monicaca.controller;

import com.monicaca.bean.User;
import com.monicaca.service.Outbound_orderService;
import com.monicaca.service.Storage_orderService;
import com.monicaca.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HelloWorldController {
	
	@Autowired
	UserService userService;
	@Autowired
	Storage_orderService storage_orderService;
	@Autowired
	Outbound_orderService outbound_orderService;
	@Autowired
    HttpServletRequest request;

	@GetMapping("/")
	public String indexView(
			Model model){
        model.addAttribute("count",userService.count());
        model.addAttribute("storage_count",storage_orderService.count());
        model.addAttribute("outbound_count",outbound_orderService.count());
		return "index";
	}
	@GetMapping("/login")
	public String loginView(
			Model model){

		return "login";
	}
	@GetMapping("/logout")
	public String logoutView(
			Model model){
        request.getSession().removeAttribute("user");
		return "login";
	}
	@PostMapping("/login")
	public String login(Model model,User user){
        try {
            User loginuser = userService.findByUserName(user);
            if (!loginuser.getPassword().equals(user.getPassword())){
                model.addAttribute("msg","用户名或者密码不正确");
                return "login";
            }else {
                request.getSession().setAttribute("user",loginuser);
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("msg","用户不存在");
            return "login";
        }
        //转发到index
        return "redirect:/";
    }
}
