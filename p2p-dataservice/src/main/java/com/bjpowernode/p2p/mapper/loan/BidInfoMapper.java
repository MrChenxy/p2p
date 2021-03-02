package com.bjpowernode.p2p.mapper.loan;


import com.bjpowernode.p2p.po.ext.LoanBidInfo;
import com.bjpowernode.p2p.po.ext.UserBidInfo;
import com.bjpowernode.p2p.po.loan.BidInfo;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

public interface BidInfoMapper {

    /**自定义开始*/
    BigDecimal selectSumTotalBidMoney();

    /**自定义完成*/
    int deleteByPrimaryKey(Integer id);

    /**查询某个产品的最近10投资记录*/
    List<UserBidInfo> selectRecentlyBidInfoList(@Param("loanId") Integer loanId);

    /**查询某个用户的分页的投资记录*/
    List<LoanBidInfo> selectBidInfoPageByUserId(@Param("userId") Integer userId,
                                                @Param("offSet") int offSet,
                                                @Param("pageSize") Integer pageSize);

    /**根据产品id,查询他的所有投资记录*/
    List<BidInfo> selectAllBidInfoByLoan(@Param("loanId") Integer loanId);

    int insert(BidInfo record);

    int insertSelective(BidInfo record);

    BidInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BidInfo record);

    int updateByPrimaryKey(BidInfo record);


}