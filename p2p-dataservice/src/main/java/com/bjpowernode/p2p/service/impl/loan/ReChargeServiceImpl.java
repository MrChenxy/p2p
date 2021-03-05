package com.bjpowernode.p2p.service.impl.loan;/**
 * @author gsyzh
 * @create 2021-03-04 18:28
 */

import com.bjpowernode.p2p.po.loan.RechargeRecord;
import com.bjpowernode.p2p.service.loan.ReChargeService;

import java.math.BigDecimal;
import java.util.List;

/**
 *
 *@author Mr.chenxy
 *@date 2021/3/4
 */
public class ReChargeServiceImpl implements ReChargeService {
    @Override
    public List<RechargeRecord> queryPageByUserId(Integer userId, Integer pageNo, Integer pageSize) {
        return null;
    }

    @Override
    public int createRechargeRecord(RechargeRecord rechargeRecord) {
        return 0;
    }

    @Override
    public RechargeRecord queryRechargeByRechargeNo(String rechareNo) {
        return null;
    }

    @Override
    public int recharge(Integer userId, Integer rechargeId, BigDecimal money) {
        return 0;
    }

    @Override
    public List<RechargeRecord> queryRecharge(Integer status, String channel) {
        return null;
    }

    @Override
    public int modifyRechargeStatus(Integer rechargeId, Integer status) {
        return 0;
    }
}
