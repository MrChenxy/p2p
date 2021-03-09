package com.bjpowernode.p2p.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.bjpowernode.p2p.po.user.FinanceAccount;
import com.bjpowernode.p2p.po.user.User;
import com.bjpowernode.p2p.service.RealNameService;
import com.bjpowernode.p2p.service.SendCodeService;
import com.bjpowernode.p2p.service.loan.BidInfoService;
import com.bjpowernode.p2p.service.loan.ReChargeService;
import com.bjpowernode.p2p.service.user.FinanceAccountService;
import com.bjpowernode.p2p.service.user.UserService;
import com.bjpowernode.p2p.vo.ReturnObject;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;


/**
 *
 *@author Mr.chenxy
 *@date 2021/3/5
 */
@Controller
public class UserController {

    @Reference(interfaceClass = UserService.class,version = "1.0.0",check = false)
    private UserService userService;

    @Reference(interfaceClass = FinanceAccountService.class,version = "1.0.0",check = false)
    private FinanceAccountService financeAccountService;

    @Reference(interfaceClass = BidInfoService.class,version = "1.0.0",check = false)
    private BidInfoService bidInfoService;

    @Reference(interfaceClass = ReChargeService.class,version = "1.0.0",check = false)
    private ReChargeService reChargeService;

    @Resource
    private SendCodeService sendCodeService;

    @Resource
    private RealNameService realNameService;

    /**
     * 跳转注册页面
     * @return
     */
    @RequestMapping("/loan/page/register")
    public String register(){
        return "register";
    }

    /**
     * 手机号码判断
     */
    @ResponseBody
    @RequestMapping("loan/checkPhone")
    public Object checkPhone(@RequestParam("phone") String phone){
        ReturnObject returnObject = new ReturnObject();
        User user = userService.queryPhone(phone);
        if (user != null){
            returnObject.setCode(400);
            returnObject.setMessage("该手机号已经注册过,请勿重复注册!");
            return returnObject;
        }
        returnObject.setCode(200);
        return returnObject;
    }


    /**
     * 验证码发送
     * @param phone
     * @return
     */
    @RequestMapping("/loan/sendCode")
    public void sendCode(@RequestParam("phone") String phone){
        boolean flag = sendCodeService.sendSmsCode(phone);
        if (flag == true){
            System.out.println("信息发送成功!");
        }else {
            System.out.println("信息发送失败!");
        }
    }

    /**
     * 注册用户
     * @param phone
     */
    @ResponseBody
    @RequestMapping("/loan/register")
    public Object userRegister(@RequestParam("phone") String phone,
                               @RequestParam("pwd") String pwd,
                               @RequestParam("code") String code,
                               HttpSession session){
        ReturnObject returnObject = new ReturnObject();
        boolean flag = sendCodeService.checkCode(phone, code);
        if (flag){
            //注册用户
            User user = userService.register(phone, pwd);
            if (user != null){
                session.setAttribute("user",user);
                returnObject.setCode(200);
                return returnObject;
            }
            returnObject.setCode(400);
            return returnObject;
        }
        returnObject.setCode(400);
        returnObject.setMessage("验证码输入错误!");
        return returnObject;
    }


    /**
     * 跳转到实名验证页面
     */
    @RequestMapping("/loan/page/realName")
    public String realNameCenter(){
        return "realName";
    }

    /**
     * 验证用户身份信息身份正确
     * @param phone    手机号码
     * @param realName 姓名
     * @param idCard   身份证号码
     * @param code     验证码
     * @return
     */
    @ResponseBody
    @RequestMapping("/loan/realName")
    public Object realName(@RequestParam("phone") String phone,
                           @RequestParam("realName") String realName,
                           @RequestParam("idCard") String idCard,
                           @RequestParam("code") String code,
                           HttpSession session){
        ReturnObject obj = new ReturnObject();
        //先验证用户输入的验证码是否正确
        boolean flag = sendCodeService.checkCode(phone, code);
        if (flag){
            //进行用户身份信息验证
            boolean resu = realNameService.checkName(realName, idCard);
            //如果实名认证成功, 则更新用户数据
            if (resu){
                User user = new User();
                user.setName(realName);
                user.setIdCard(idCard);
                user.setPhone(phone);
                int nums = userService.modifyUser(user);
                if (nums > 0){
                    user = userService.queryPhone(phone);
                    session.setAttribute("user",user);
                }
                obj.setCode(200);
                return obj;
            }
            obj.setCode(400);
            obj.setMessage("身份信息输入错误,请重新输入");
            return obj;
        }
        obj.setCode(400);
        obj.setMessage("验证码输入错误,请重新输入!");
        return obj;
    }

    /**
     * 实名认证后跳转到个人中心
     * @return
     */
    @RequestMapping("/loan/myCenter")
    public String myCenter(HttpSession session, Model model){
        try {
            User user = (User) session.getAttribute("user");
            if (user != null){
                FinanceAccount financeAccount = financeAccountService.queryAccount(user.getId());
                BigDecimal availableMoney = financeAccount.getAvailableMoney();
                session.setAttribute("avaiableMoney",availableMoney);
                //查询投资记录
                PageInfo pageInfo = bidInfoService.queryLoanBidInfoByPage(user.getId(), 1, 5);
                model.addAttribute("loanBidInfos",pageInfo);
                //查询最近充值
                PageInfo recharPageInfo = reChargeService.queryPageByUserId(user.getId(), 1, 5);
                model.addAttribute("rechargeInfos",recharPageInfo);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return "myCenter";
    }

    /**
     * 查询个人中心的全部投资
     * @return
     */
    @RequestMapping("/loan/myInvest")
    public String myInvest(){
        return "myInvest";
    }
    /**
     * 查询个人中心的全部充值
     * @return
     */
    @RequestMapping("/loan/myRecharge")
    public String myRecharge(){
        return "myRecharge";
    }
    /**
     * 查询个人中心的全部收益计划
     * @return
     */
    @RequestMapping("/loan/myIncome")
    public String myIncome(){
        return "myIncome";
    }

    /**
     * 用户上传头像
     * @return
     */
    @RequestMapping("/loan/uploadHeader")
    public String uploadHeader(){

        return "myIncome";
    }



    /**
     * 退出个人登录
     * @return
     */
    @RequestMapping("/loan/logout")
    public String uploadHeader(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/index";
    }





}
