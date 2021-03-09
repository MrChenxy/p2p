package com.bjpowernode.p2p.service.impl.loan;/**
 * @author gsyzh
 * @create 2021-03-04 18:27
 */

import com.alibaba.dubbo.config.annotation.Service;
import com.bjpowernode.p2p.mapper.loan.IncomeRecordMapper;
import com.bjpowernode.p2p.mapper.loan.LoanInfoMapper;
import com.bjpowernode.p2p.mapper.user.FinanceAccountMapper;
import com.bjpowernode.p2p.po.loan.IncomeRecord;
import com.bjpowernode.p2p.po.loan.LoanInfo;
import com.bjpowernode.p2p.po.user.FinanceAccount;
import com.bjpowernode.p2p.service.loan.BidInfoService;
import com.bjpowernode.p2p.service.loan.IncomeService;
import com.bjpowernode.p2p.service.user.FinanceAccountService;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 *
 *@author Mr.chenxy
 *@date 2021/3/4
 */
@Component
@Transactional
@Service(interfaceClass = IncomeService.class,version = "1.0.0",timeout = 35000)
public class IncomeServiceImpl implements IncomeService {

    @Resource
    LoanInfoMapper loanInfoMapper;

    @Resource
    private IncomeRecordMapper incomeRecordMapper;

    @Resource
    private FinanceAccountMapper financeAccountMapper;


    /**
     * 定时任务, 查询所有的loan,看是否满标, 满标的开始收益
     */
    @Override
    public void generateIncomePlan() {
        List<LoanInfo> loanInfos = loanInfoMapper.selectAllLoanInfo();
        //遍历所有的loan,如果满标,修改
        for (LoanInfo loanInfo : loanInfos) {
            if (loanInfo.getLeftProductMoney().compareTo(BigDecimal.ZERO) == 0){

            }
        }
    }

    @Override
    public void generateIncomeBack() {
        //遍历所有的状态为0的income_表,如果收益日期等于为现在,finance表添加本金加利息,income表状态改为1
        List<IncomeRecord> incomeRecords = incomeRecordMapper.selectIncomeByStatus(0);
        Date date = new Date();
        for (IncomeRecord incomeRecord : incomeRecords) {
            Date incomeDate = incomeRecord.getIncomeDate();
            if (DateUtils.isSameDay(incomeDate,date)){
                int nums = financeAccountMapper.updateFinanceAccountIncomeBack(incomeRecord.getUid(), incomeRecord.getBidMoney(), incomeRecord.getIncomeMoney());
                if (nums < 1){
                    throw new RuntimeException("资金更改失败!");
                }
                //更改income表的状态
                incomeRecord.setIncomeStatus(1);
                int i = incomeRecordMapper.updateByPrimaryKeySelective(incomeRecord);
                if (i < 1){
                    throw new RuntimeException("收益表状态值修改失败!");
                }
            }
        }
    }

    @Override
    public int insertIncome(IncomeRecord incomeRecord) {
        int nums = incomeRecordMapper.insertSelective(incomeRecord);
        return nums;
    }

    @Override
    public List<IncomeRecord> queryAllIncomeByLoanId(Integer loanId) {
        List<IncomeRecord> incomeRecords = incomeRecordMapper.selectAllIncomeByLoanId(loanId);
        return incomeRecords;
    }

    @Override
    public int updateIncomeByLoanIdAndStatus(Integer loanId, Date date, Integer status) {
        int nums = incomeRecordMapper.updataByLoanId(loanId, date, status);
        int i = incomeRecordMapper.updataLoanOfStatus(loanId, status, 0);
        return nums;
    }
}
