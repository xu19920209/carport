package com.tuyue.web.login.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: 王金海
 * @Description:
 * @Date: Created by Administrator on 2017/9/19.
 * @Modified By:
 */
@RestController
public class CeshiController {
    @GetMapping("ces")
    public String ceshi(String name,String password){


        return name+password;
    }
}
