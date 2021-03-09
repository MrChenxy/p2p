package com.bjpowernode.p2p.service.impl.loan;/**
 * @author gsyzh
 * @create 2021-03-04 18:26
 */

import com.alibaba.dubbo.config.annotation.Service;
import com.bjpowernode.p2p.common.ErrorEnum;
import com.bjpowernode.p2p.mapper.loan.BidInfoMapper;
import com.bjpowernode.p2p.po.ext.LoanBidInfo;
import com.bjpowernode.p2p.po.ext.UserBidInfo;
import com.bjpowernode.p2p.po.loan.BidInfo;
import com.bjpowernode.p2p.service.loan.BidInfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
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
        List<UserBidInfo> userBidInfos = bidInfoMapper.selectRecentlyBidInfoList(loanId);
        return userBidInfos;
    }

    @Override
    public PageInfo queryLoanBidInfoByPage(Integer userId, Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        List<LoanBidInfo> loanBidInfos = bidInfoMapper.selectBidInfoPageByUserId(userId);
        PageInfo pageInfo = new PageInfo<>(loanBidInfos);
        return pageInfo;
    }

    @Override
    public ErrorEnum invest(Integer userId, Integer loanId, BigDecimal bidMoney) {
        return null;
    }

    @Override
    public int insertBidInfoOfLoan(Integer userId, Integer loanId, BigDecimal bidMoney) {
        BidInfo bidInfo = new BidInfo();
        bidInfo.setLoanId(loanId);
        bidInfo.setUid(userId);
        bidInfo.setBidMoney(bidMoney);
        bidInfo.setBidTime(new Date());
        bidInfo.setBidStatus(1);
        //返回的为插入的id主键
        int nums = bidInfoMapper.insertSelective(bidInfo);
        return nums;
    }

    @Override
    public List<BidInfo> queryAllBidInfoByLoanId(Integer loanId) {
        List<BidInfo> bidInfos = bidInfoMapper.selectAllBidInfoByLoan(loanId);
        return bidInfos;
    }
}
