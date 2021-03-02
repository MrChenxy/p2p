package com.bjpowernode.p2p.service.user;

import com.bjpowernode.p2p.po.user.FinanceAccount;


public interface FinanceAccountService {
    FinanceAccount queryAccount(Integer userId);
}
