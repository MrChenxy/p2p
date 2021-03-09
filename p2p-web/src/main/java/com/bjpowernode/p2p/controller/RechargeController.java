package com.bjpowernode.p2p.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Mr.chenxy
 * @date 2021/3/8
 */
@Controller
public class RechargeController {


    /**
     * 跳转到充值页面
     * @return
     */
    @RequestMapping("/loan/page/toRecharge")
    public String myInvest(){
        return "toRecharge";
    }

}
