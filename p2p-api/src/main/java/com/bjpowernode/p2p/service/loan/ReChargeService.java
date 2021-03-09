package com.bjpowernode.p2p.service.loan;


import com.bjpowernode.p2p.po.loan.RechargeRecord;
import com.github.pagehelper.PageInfo;

import java.math.BigDecimal;
import java.util.List;

public interface ReChargeService {

    /**
     * 分页查询充值记录（充值成功的）
     * @param userId 用户id
     * @param pageNo 第几页
     * @param pageSize 每页大小
     * @return
     */
    PageInfo queryPageByUserId(Integer userId,
                               Integer pageNo,
                               Integer pageSize);


    /**
     * 创建充值记录
     * @param rechargeRecord
     * @return
     */
    int createRechargeRecord(RechargeRecord rechargeRecord);


    /**
     * 根据商户自己的订单号查询充值记录
     * @param rechareNo
     * @return
     */
    RechargeRecord queryRechargeByRechargeNo(String rechareNo);


    /**
     * 完成充值
     * @return
     */
    int recharge(Integer userId, Integer rechargeId, BigDecimal money);


    /**
     * 查询订单状态
     */
    List<RechargeRecord> queryRecharge(Integer status, String channel);

    /**
     * 更新充值记录的状态
     * @param rechargeId
     * @param status
     * @return
     */
    int modifyRechargeStatus(Integer rechargeId, Integer status);
}
