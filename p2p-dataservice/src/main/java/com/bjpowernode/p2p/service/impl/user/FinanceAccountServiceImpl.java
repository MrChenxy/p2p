package com.bjpowernode.p2p.service.impl.user;/**
 * @author gsyzh
 * @create 2021-03-04 18:29
 */

import com.alibaba.dubbo.config.annotation.Service;
import com.bjpowernode.p2p.mapper.loan.LoanInfoMapper;
import com.bjpowernode.p2p.mapper.user.FinanceAccountMapper;
import com.bjpowernode.p2p.po.loan.LoanInfo;
import com.bjpowernode.p2p.po.user.FinanceAccount;
import com.bjpowernode.p2p.service.user.FinanceAccountService;
import com.bjpowernode.p2p.service.user.UserService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 *
 *@author Mr.chenxy
 *@date 2021/3/4
 */
@Component
@Transactional
@Service(interfaceClass = FinanceAccountService.class,version = "1.0.0",timeout = 3500)
public class FinanceAccountServiceImpl implements FinanceAccountService {

    @Resource
    private FinanceAccountMapper financeAccountMapper;

    @Resource
    private LoanInfoMapper loanInfoMapper;

    @Override
    public FinanceAccount queryAccount(Integer userId) {
        FinanceAccount financeAccount = financeAccountMapper.selectByUserId(userId);
        return financeAccount;
    }

    @Override
    public int updateAccountOfMoney(Integer uid, BigDecimal money,Integer loanId) {
        int AccountNum = financeAccountMapper.updateFinanceAccount(uid, money);
        LoanInfo loanInfo = loanInfoMapper.selectByPrimaryKey(loanId);
        int loanNums = loanInfoMapper.updateLoanInfoById(loanId, money, loanInfo.getVersion());
        return loanNums + AccountNum;
    }
}
