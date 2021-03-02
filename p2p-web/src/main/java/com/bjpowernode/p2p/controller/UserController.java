package com.bjpowernode.p2p.controller;/**
 * @author gsyzh
 * @create 2021-03-02 20:42
 */

import com.alibaba.dubbo.config.annotation.Reference;
import com.bjpowernode.p2p.service.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 *@author Mr.chenxy
 *@date 2021/3/2
 */
@Controller
public class UserController {

    @Reference(interfaceClass = UserService.class,version = "1.0.0",check = false)
    private UserService userService;

    @RequestMapping("/user")
    @ResponseBody
    public String getUser(){
        int i = userService.sumTotalUser();
        return "学生共有:" + i + "人";
    }



}
