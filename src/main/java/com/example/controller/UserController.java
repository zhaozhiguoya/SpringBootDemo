package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author：zhaozg
 * @Date：2024/4/19 9:29
 * @Desc：
 */
@Controller
public class UserController {
    @ResponseBody
    @RequestMapping("/login")
    public String index(){
        return "login";
    }
}

