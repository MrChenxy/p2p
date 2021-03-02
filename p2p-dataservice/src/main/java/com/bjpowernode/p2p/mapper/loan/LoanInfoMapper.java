package com.bjpowernode.p2p.mapper.loan;


import com.bjpowernode.p2p.po.loan.LoanInfo;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

public interface LoanInfoMapper {
    /**自定义开始*/
    /**
     * 平均历史年华收益率
     * @return
     */
    BigDecimal selectHistoryAvgRate();

    /**
     * 按产品类型，分页查询， Order by Rate desc
     * @return
     */
    List<LoanInfo> selectLoanInfoByProductTypePage(@Param("type") Integer productType,
                                                   @Param("offSet") Integer offSet,
                                                   @Param("rows") Integer rows);


    /**
     * 按产品类型，分页查询， Order by release_time desc
     * @return
     */
    List<LoanInfo> selectLoanInfoByProductTypeOrderByTime(@Param("type") Integer productType,
                                                          @Param("offSet") Integer offSet,
                                                          @Param("rows") Integer rows);

    /***
     * 按产品类型，分页查询，计算总记录数
     */
    int countRowsByProductType(@Param("type") Integer productType);


    /**
     * 根据产品id，查询单一产品
     * @param id
     * @return
     */
    LoanInfo selectByPrimaryKey(@Param("id") Integer id);


    /***
     * 使用乐观锁的更新
     */
    int updateLoanInfoById(@Param("loanId") Integer loanId,
                           @Param("bidMoney") BigDecimal bidMoney,
                           @Param("ver") Integer version);


    /**
     * 按产品状态查询产品
     * @param status 状态 0 ，1， 2
     * @return
     */
    List<LoanInfo> selectByProductStatus(@Param("status") Integer status);

    /**自定义完成*/
    int deleteByPrimaryKey(Integer id);

    int insert(LoanInfo record);

    int insertSelective(LoanInfo record);



    int updateByPrimaryKeySelective(LoanInfo record);

    int updateByPrimaryKey(LoanInfo record);
}