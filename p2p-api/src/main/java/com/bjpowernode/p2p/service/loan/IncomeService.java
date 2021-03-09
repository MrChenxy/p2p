package com.bjpowernode.p2p.service.loan;

import com.bjpowernode.p2p.po.loan.IncomeRecord;

import java.util.Date;
import java.util.List;

public interface IncomeService {

    /**
     * 生成收益计划
     */
    void generateIncomePlan();

    /**
     * 收益返还
     */
    void generateIncomeBack();

    int insertIncome(IncomeRecord incomeRecord);

    List<IncomeRecord> queryAllIncomeByLoanId(Integer loanId);

    int updateIncomeByLoanIdAndStatus(Integer loanId , Date date, Integer status);
}
