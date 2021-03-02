package com.bjpowernode.p2p.mapper.user;


import com.bjpowernode.p2p.po.user.FinanceAccount;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;


public interface FinanceAccountMapper {

    // 自定义方法开始

    /**
     * 根据userId，查询FinanceAccount
     * @param userId
     * @return
     */
    FinanceAccount selectByUserId(@Param("userId") Integer userId);

    /**
     * 给一行记录上锁
     * @param userId
     * @return
     */
    FinanceAccount selectByUserIdForUpdate(@Param("userId") Integer userId);

    /***
     * 更新账号的金额
     * @param id
     * @return
     */
    int updateFinanceAccount(@Param("userId") Integer userId,
                             @Param("bidMoney") BigDecimal bidMoney);


    /**
     * 收益到期，返回给资金账户
     * @param userId
     * @param bidMoney
     * @param incomeMoney
     * @return
     */
    int updateFinanceAccountIncomeBack(@Param("userId") Integer userId,
                                       @Param("bidMoney") BigDecimal bidMoney,
                                       @Param("incomeMoney") BigDecimal incomeMoney);



    int updateFinaneAccountByRecharge(@Param("userId") Integer userId,
                                      @Param("rechargeMoney") BigDecimal bidMoney);

    //自定义方法完成
    int deleteByPrimaryKey(Integer id);

    int insert(FinanceAccount record);

    int insertSelective(FinanceAccount record);

    FinanceAccount selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FinanceAccount record);

    int updateByPrimaryKey(FinanceAccount record);
}