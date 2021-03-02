package com.bjpowernode.p2p.service.loan;


import com.bjpowernode.p2p.common.ErrorEnum;
import com.bjpowernode.p2p.po.ext.LoanBidInfo;
import com.bjpowernode.p2p.po.ext.UserBidInfo;

import java.math.BigDecimal;
import java.util.List;

public interface BidInfoService {

    /**
     * 累计成交金额
     * @return
     */
    BigDecimal querySumBidMoney();

    /**
     * 某个产品最近的10条投资记录
     */
    List<UserBidInfo> queryRecentlyBidInfoList(Integer loanId);


    /**
     * 查询某个用户的分页的投资记录
     */
    List<LoanBidInfo> queryLoanBidInfoByPage(Integer userId,
                                             Integer pageNo,
                                             Integer pageSize);


    /**
     * 投资
     */
    ErrorEnum invest(Integer userId, Integer loanId, BigDecimal bidMoney);
}
