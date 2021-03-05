package com.bjpowernode.p2p.service.impl.loan;/**
 * @author gsyzh
 * @create 2021-03-04 18:12
 */

import com.alibaba.dubbo.config.annotation.Service;
import com.bjpowernode.p2p.mapper.loan.LoanInfoMapper;
import com.bjpowernode.p2p.po.loan.LoanInfo;
import com.bjpowernode.p2p.service.loan.LoanInfoService;
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
@Service(interfaceClass = LoanInfoService.class,version = "1.0.0",timeout = 35000)
public class LoanInfoServiceImpl implements LoanInfoService {

    @Resource
    private LoanInfoMapper loanInfoMapper;

    @Override
    public BigDecimal queryHistoryAvgRate() {
        return loanInfoMapper.selectHistoryAvgRate();
    }

    @Override
    public List<LoanInfo> queryLoanInfoByProductTypePage(Integer productType, Integer pageNo, Integer pageSize) {
        //PageHelper.startPage(pageNo, pageSize);
        return loanInfoMapper.selectLoanInfoByProductTypePage(productType, pageNo, pageSize);
    }

    @Override
    public List<LoanInfo> queryLoanInfoListByProductType(Integer productType, Integer pageNo, Integer pageSize) {
        return null;
    }

    @Override
    public int countRowByProductType(Integer productType) {
        return 0;
    }

    @Override
    public LoanInfo queryLoanInfoById(Integer loanId) {
        return null;
    }
}
