package com.bjpowernode.p2p.controller;/**
 * @author gsyzh
 * @create 2021-03-04 20:08
 */

import com.alibaba.dubbo.config.annotation.Reference;
import com.bjpowernode.p2p.po.ext.UserBidInfo;
import com.bjpowernode.p2p.po.loan.IncomeRecord;
import com.bjpowernode.p2p.po.loan.LoanInfo;
import com.bjpowernode.p2p.po.user.FinanceAccount;
import com.bjpowernode.p2p.po.user.User;
import com.bjpowernode.p2p.service.loan.BidInfoService;
import com.bjpowernode.p2p.service.loan.IncomeService;
import com.bjpowernode.p2p.service.loan.LoanInfoService;
import com.bjpowernode.p2p.service.user.FinanceAccountService;
import com.bjpowernode.p2p.vo.ReturnObject;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * @author Mr.chenxy
 * @date 2021/3/4
 */
@Controller
public class LoanInfoController {

    @Reference(interfaceClass = LoanInfoService.class, version = "1.0.0", timeout = 3500)
    private LoanInfoService loanInfoService;

    @Reference(interfaceClass = BidInfoService.class, version = "1.0.0", timeout = 3500)
    private BidInfoService bidInfoService;

    @Reference(interfaceClass = FinanceAccountService.class, version = "1.0.0", check = false)
    private FinanceAccountService financeAccountService;

    @Reference(interfaceClass = IncomeService.class, version = "1.0.0", check = false)
    private IncomeService incomeService;

    /**
     * 更多产品页面
     *
     * @param model
     * @param ptype
     * @param pageNo
     * @param pageSize
     * @return
     */
    @RequestMapping("/loan/loan")
    public String loanIndex(Model model,
                            @RequestParam(name = "ptype", required = false, defaultValue = "2") int ptype,
                            @RequestParam(name = "page", required = false, defaultValue = "1") int pageNo,
                            @RequestParam(name = "size", required = false, defaultValue = "9") int pageSize
    ) {
        PageInfo pageInfo = loanInfoService.queryLoanInfoByTypeAndPage(ptype, pageNo, pageSize);
        model.addAttribute("loanInfoList", pageInfo);
        return "loan";
    }

    /**
     * 投资详情页面
     *
     * @param model
     * @param loanId
     * @return
     */
    @RequestMapping("/loan/loanInfo")
    public String loanInfo(Model model, Integer loanId, HttpSession session) {
        LoanInfo loanInfo = loanInfoService.queryLoanInfoById(loanId);
        model.addAttribute("loanInfo", loanInfo);
        List<UserBidInfo> userBidInfos = bidInfoService.queryRecentlyBidInfoList(loanId);
        model.addAttribute("userBidInfos", userBidInfos);
        try {
            User user = (User) session.getAttribute("user");
            if (user != null) {
                FinanceAccount financeAccount = financeAccountService.queryAccount(user.getId());
                BigDecimal availableMoney = financeAccount.getAvailableMoney();
                model.addAttribute("availableMoney", availableMoney);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "loanInfo";
    }

    /**
     * ajax立即投资
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/bid/invest")
    public ReturnObject uploadHeader(@RequestParam("loanId") Integer loanId,
                                     @RequestParam("money") BigDecimal money,
                                     @RequestParam("shouyi") BigDecimal income,
                                     @RequestParam("type") Integer type,
                                     HttpSession session) {
        ReturnObject obj = new ReturnObject();
        //由于在前端已经验证投资的金额,只有是正常能投资的才会发送给后端
        User user = (User) session.getAttribute("user");
        if (user == null) {
            obj.setCode(400);
            obj.setMessage("您还未登录,请先登录账号!");
            return obj;
        }
        //更新finance_account表,查询用户的可用金额是否可以购买,更新用户可用金额
        FinanceAccount financeAccount = financeAccountService.queryAccount(user.getId());
        BigDecimal availableMoney = financeAccount.getAvailableMoney();
        if (availableMoney == null || (availableMoney.compareTo(money) == -1)) {
            obj.setCode(400);
            obj.setMessage("你的钱不足!请充值!");
            return obj;
        }
        //更新用户资金表和产品剩余金额表
        financeAccountService.updateAccountOfMoney(user.getId(), money, loanId);
        //生成bid_info投资表, 记录投标信息
        int bid = bidInfoService.insertBidInfoOfLoan(user.getId(), loanId, money);
        //生成income_record表, 记录投标的收益状态
        IncomeRecord iRecord = new IncomeRecord();
        iRecord.setUid(user.getId());
        iRecord.setLoanId(loanId);
        iRecord.setBidId(bid);
        iRecord.setBidMoney(money);
        iRecord.setIncomeMoney(income.subtract(money));
        iRecord.setIncomeStatus(2);
        int nums = incomeService.insertIncome(iRecord);
        if (nums != 1) {
            throw new RuntimeException("添加收益表失败");
        }
        //更新loan_info表,查询是否已经满标,满标更改状态,并添加满标时间
        LoanInfo loanInfo = loanInfoService.queryLoanInfoById(loanId);
        if (loanInfo.getLeftProductMoney().compareTo(BigDecimal.ZERO) == 0) {
            loanInfo.setId(loanId);
            loanInfo.setProductStatus(1);
            loanInfo.setProductFullTime(new Date());
            loanInfoService.updateLoanInfoById(loanInfo);
            //更新完loan表后, 根据投资记录表中loan_id查询所有投资记录 并更新时间收益
            //判断loan的type
            if (loanInfo.getProductType() == 0) {
                //此时loan 的type为天
                Integer dates = loanInfo.getCycle();
                Date date = DateUtils.addDays(new Date(), dates);
                int num = incomeService.updateIncomeByLoanIdAndStatus(loanId, date, 2);
                if (num < 1) {
                    throw new RuntimeException("1收益表日期更新失败!");
                }
            } else {
                Integer month = loanInfo.getCycle();
                Date date = DateUtils.addMonths(new Date(), month);
                int num = incomeService.updateIncomeByLoanIdAndStatus(loanId, date, 2);
                if (num < 1) {
                    throw new RuntimeException("2收益表日期更新失败!");
                }
            }
        }
        obj.setCode(200);
        return obj;
    }
}
