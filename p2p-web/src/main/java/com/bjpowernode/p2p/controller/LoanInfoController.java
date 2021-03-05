package com.bjpowernode.p2p.controller;/**
 * @author gsyzh
 * @create 2021-03-04 20:08
 */

import com.alibaba.dubbo.config.annotation.Reference;
import com.bjpowernode.p2p.common.Constants;
import com.bjpowernode.p2p.po.loan.LoanInfo;
import com.bjpowernode.p2p.service.loan.LoanInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 *
 *@author Mr.chenxy
 *@date 2021/3/4
 */
@Controller
@RequestMapping("/loan/")
public class LoanInfoController {

    @Reference(interfaceClass = LoanInfoService.class,version = "1.0.0",timeout = 3500)
    private LoanInfoService loanInfoService;

    @RequestMapping("/loan")
    public String loanIndex(Model model){
        List<LoanInfo> loanInfos = loanInfoService.queryLoanInfoByProductTypePage(
                Constants.S_LOANINFO_PRODUCT_TYPE, 0, 64
        );
        model.addAttribute("loanInfoList",loanInfos);
        return "loan";
    }



}
