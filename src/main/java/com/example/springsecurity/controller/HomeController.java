package com.example.springsecurity.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
    @ResponseBody
    @GetMapping("/admin")
    public String admin() {
        return "admin";  // 渲染 admin.html 模板
    }

    @GetMapping("/")
    public String home(Model model) {
        // 获取当前的 Authentication 对象
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // 如果用户已认证，显示用户名
        if (authentication != null && authentication.isAuthenticated()) {
            model.addAttribute("username", authentication.getName());
        } else {
            model.addAttribute("username", "Guest");
        }

        return "index";  // 渲染 index.html 模板
    }

    @GetMapping("/login")
    public String login() {
        System.out.println("login.");
        return "login";  // 渲染 login.html 模板
    }
}