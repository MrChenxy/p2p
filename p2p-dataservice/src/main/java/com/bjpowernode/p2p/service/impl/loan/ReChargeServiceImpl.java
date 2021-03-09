package com.bjpowernode.p2p.service.impl.loan;/**
 * @author gsyzh
 * @create 2021-03-04 18:28
 */

import com.alibaba.dubbo.config.annotation.Service;
import com.bjpowernode.p2p.mapper.loan.LoanInfoMapper;
import com.bjpowernode.p2p.mapper.loan.RechargeRecordMapper;
import com.bjpowernode.p2p.po.loan.RechargeRecord;
import com.bjpowernode.p2p.service.loan.LoanInfoService;
import com.bjpowernode.p2p.service.loan.ReChargeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 *@author Mr.chenxy
 *@date 2021/3/4
 */

@Component
@Service(interfaceClass = ReChargeService.class,version = "1.0.0",timeout = 35000)
public class ReChargeServiceImpl implements ReChargeService {

    @Resource
    private RechargeRecordMapper rechargeRecordMapper;

    @Override
    public PageInfo queryPageByUserId(Integer userId, Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        List<RechargeRecord> rechargeRecords = rechargeRecordMapper.selectPageByUserId(userId);
        PageInfo pageInfo = new PageInfo(rechargeRecords);
        return pageInfo;
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
