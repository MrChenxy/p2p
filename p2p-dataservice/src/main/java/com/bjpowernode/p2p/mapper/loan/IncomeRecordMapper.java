package com.bjpowernode.p2p.mapper.loan;

import com.bjpowernode.p2p.po.loan.IncomeRecord;

import java.util.Date;
import java.util.List;

public interface IncomeRecordMapper {

    /**
     * @return 到期的记录
     */
    List<IncomeRecord> selectExpireRecord();

    List<IncomeRecord> selectAllIncomeByLoanId(Integer loanId);

    /**
     * 根据income的状态查询income
     * @return
     */
    List<IncomeRecord> selectIncomeByStatus(Integer status);

    //这两个update写错地方了,但懒得改了
    int updataByLoanId(Integer loanId , Date date,Integer status);
    int updataLoanOfStatus(Integer loanId ,Integer status,Integer afterStatus);

    int deleteByPrimaryKey(Integer id);

    int insert(IncomeRecord record);

    int insertSelective(IncomeRecord record);

    IncomeRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(IncomeRecord record);

    int updateByPrimaryKey(IncomeRecord record);
}