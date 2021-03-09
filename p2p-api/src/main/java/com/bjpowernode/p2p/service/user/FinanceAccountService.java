package com.bjpowernode.p2p.service.user;

import com.bjpowernode.p2p.po.user.FinanceAccount;

import java.math.BigDecimal;


public interface FinanceAccountService {

    FinanceAccount queryAccount(Integer userId);

    int updateAccountOfMoney(Integer uid, BigDecimal money,Integer loanId);

}
