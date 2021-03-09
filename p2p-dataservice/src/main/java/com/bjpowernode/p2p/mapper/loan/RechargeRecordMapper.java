package com.bjpowernode.p2p.mapper.loan;


import com.bjpowernode.p2p.po.loan.RechargeRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RechargeRecordMapper {

    /**分页查询充值记录（充值成功的）*/
    List<RechargeRecord> selectPageByUserId(@Param("userId") Integer userId);


    /**
     * 根据充值订单号查询订单记录
     * @param rechargeNo
     * @return
     */
    RechargeRecord selectByRechargeNo(@Param("rechargeNo") String rechargeNo);

    /**
     * 查询某个状态，某个渠道的充值记录
     * @param status
     * @param channel
     * @return
     */
    List<RechargeRecord> selectRecordByStatus(@Param("status") Integer status, @Param("channel") String channel);


    /**
     * 更新某个记录的状态
     * @param rechargeId
     * @param status
     * @return
     */
    int updateStatus(@Param("rechargeId") Integer rechargeId, @Param("status") Integer status);


    int deleteByPrimaryKey(Integer id);

    int insert(RechargeRecord record);

    int insertSelective(RechargeRecord record);

    RechargeRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RechargeRecord record);

    int updateByPrimaryKey(RechargeRecord record);


}