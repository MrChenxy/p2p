package com.bjpowernode.p2p.controller;/**
 * @author gsyzh
 * @create 2021-03-04 14:37
 */

import com.alibaba.dubbo.config.annotation.Reference;
import com.bjpowernode.p2p.common.Constants;
import com.bjpowernode.p2p.po.loan.LoanInfo;
import com.bjpowernode.p2p.service.loan.BidInfoService;
import com.bjpowernode.p2p.service.loan.LoanInfoService;
import com.bjpowernode.p2p.service.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.List;


/**
 *
 *@author Mr.chenxy
 *@date 2021/3/4
 */
@Controller
public class IndexController {

    @Reference(interfaceClass = UserService.class,version = "1.0.0",check = false)
    private UserService userService;

    @Reference(interfaceClass = BidInfoService.class,version = "1.0.0",check = false)
    private BidInfoService bidInfoService;

    @Reference(interfaceClass = LoanInfoService.class,version = "1.0.0",timeout = 3500)
    private LoanInfoService loanInfoService;

    @RequestMapping({"/index","/"})
    public String index(Model model,
                        @RequestParam(name="page",required = true, defaultValue = "1") int pageNo,
                        @RequestParam(name="size",required = true, defaultValue = "8") int pageSize
                        ){

        //计算年利率收益
        BigDecimal bigDecimal1 = loanInfoService.queryHistoryAvgRate();
        model.addAttribute("historyAvgRate",bigDecimal1);

        //注册会员人数
        int userCount = userService.sumTotalUser();
        model.addAttribute("totalUserNumbers",userCount);

        //累计成交金额
        BigDecimal bigDecimal = bidInfoService.querySumBidMoney();
        model.addAttribute("totalBidMoney",bigDecimal);

        //遍历新手宝
        List<LoanInfo> xLoanInfoList = loanInfoService.queryLoanInfoByProductTypePage(
                Constants.X_LOANINFO_PRODUCT_TYPE,0,8);
        //PageInfo xLoanInfo = new PageInfo(xLoanInfoList);
        model.addAttribute("xLoanInfo",xLoanInfoList);

        //遍历优选计划
        List<LoanInfo> yLoanInfoList = loanInfoService.queryLoanInfoByProductTypePage(
                Constants.Y_LOANINFO_PRODUCT_TYPE,0,8);
        //PageInfo yLoanInfo = new PageInfo(yLoanInfoList);
        model.addAttribute("yLoanInfo",yLoanInfoList);

        //遍历散标产品
        List<LoanInfo> sLoanInfoList = loanInfoService.queryLoanInfoByProductTypePage(
                Constants.S_LOANINFO_PRODUCT_TYPE,0,8);
        //PageInfo sLoanInfo = new PageInfo(sLoanInfoList);
        model.addAttribute("sLoanInfo",sLoanInfoList);
        return "index";
    }


}
