package com.bjpowernode.p2p.mapper.loan;



import com.bjpowernode.p2p.po.loan.IncomeRecord;

import java.util.List;

public interface IncomeRecordMapper {

    /**
     * @return 到期的记录
     */
    List<IncomeRecord> selectExpireRecord();

    int deleteByPrimaryKey(Integer id);

    int insert(IncomeRecord record);

    int insertSelective(IncomeRecord record);

    IncomeRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(IncomeRecord record);

    int updateByPrimaryKey(IncomeRecord record);
}