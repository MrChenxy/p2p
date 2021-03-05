package com.bjpowernode.p2p.service.impl.loan;/**
 * @author gsyzh
 * @create 2021-03-04 18:26
 */

import com.alibaba.dubbo.config.annotation.Service;
import com.bjpowernode.p2p.common.ErrorEnum;
import com.bjpowernode.p2p.mapper.loan.BidInfoMapper;
import com.bjpowernode.p2p.po.ext.LoanBidInfo;
import com.bjpowernode.p2p.po.ext.UserBidInfo;
import com.bjpowernode.p2p.service.loan.BidInfoService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 *@author Mr.chenxy
 *@date 2021/3/4
 */
@Component
@Service(interfaceClass = BidInfoService.class,version = "1.0.0",timeout = 35000)
public class BidInfoServiceImpl implements BidInfoService {

    @Resource
    private BidInfoMapper bidInfoMapper;

    @Override
    public BigDecimal querySumBidMoney() {
        return bidInfoMapper.selectSumTotalBidMoney();
    }

    @Override
    public List<UserBidInfo> queryRecentlyBidInfoList(Integer loanId) {
        return null;
    }

    @Override
    public List<LoanBidInfo> queryLoanBidInfoByPage(Integer userId, Integer pageNo, Integer pageSize) {
        return null;
    }

    @Override
    public ErrorEnum invest(Integer userId, Integer loanId, BigDecimal bidMoney) {
        return null;
    }
}
