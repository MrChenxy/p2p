package com.bjpowernode.p2p.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.bjpowernode.p2p.po.user.FinanceAccount;
import com.bjpowernode.p2p.po.user.User;
import com.bjpowernode.p2p.service.user.FinanceAccountService;
import com.bjpowernode.p2p.service.user.UserService;
import com.bjpowernode.p2p.vo.ReturnObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Mr.chenxy
 * @date 2021/3/7
 */
@Controller
public class LoginController {

    @Reference(interfaceClass = UserService.class,version = "1.0.0",check = false)
    private UserService userService;

    @Reference(interfaceClass = FinanceAccountService.class,version = "1.0.0",check = false)
    private FinanceAccountService financeAccountService;


    /**
     * 登录页面
     * @return
     */
    @RequestMapping("/loan/page/login")
    public String toLogin(String returnUrl, Model model){
        model.addAttribute("returnUrl",returnUrl);
        return "login";
    }

    @ResponseBody
    @RequestMapping("/loan/login")
    public Object login(@RequestParam("phone") String phone, @RequestParam("pwd") String pwd,
                        HttpSession session,Model model){
        ReturnObject obj = new ReturnObject();
        if (StringUtils.isEmpty(phone) || StringUtils.isEmpty(pwd)){
            obj.setCode(400);
            obj.setMessage("手机号或密码输入错误!");
            return obj;
        }else if (phone.length() != 11){
            obj.setCode(400);
            obj.setMessage("手机号格式不正确!");
            return obj;
        }else if (pwd.length() != 32){
            obj.setCode(400);
            obj.setMessage("手机号或密码输入错误!");
            return obj;
        }
        //先根据phone查询账户身份存在
        User user = userService.queryPhone(phone);
        if (user != null){
            if (pwd.equals(user.getLoginPassword())){
                obj.setCode(200);
                user.setLastLoginTime(new Date());
                userService.modifyLoginTime(user);
                session.setAttribute("user",user);
                FinanceAccount financeAccount = financeAccountService.queryAccount(user.getId());
                BigDecimal availableMoney = financeAccount.getAvailableMoney();
                session.setAttribute("avaiableMoney",availableMoney);
                return obj;
            }
            obj.setCode(400);
            obj.setMessage("手机号或密码输入错误!");
            return obj;
        }
        obj.setCode(400);
        obj.setMessage("手机号或密码输入错误!");
        return obj;
    }
}
