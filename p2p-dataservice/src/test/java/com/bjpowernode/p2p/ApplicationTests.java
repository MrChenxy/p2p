package com.bjpowernode.p2p;

import com.bjpowernode.p2p.mapper.loan.BidInfoMapper;
import com.bjpowernode.p2p.mapper.loan.IncomeRecordMapper;
import com.bjpowernode.p2p.mapper.loan.LoanInfoMapper;
import com.bjpowernode.p2p.mapper.user.FinanceAccountMapper;
import com.bjpowernode.p2p.mapper.user.UserMapper;
import com.bjpowernode.p2p.po.loan.BidInfo;
import com.bjpowernode.p2p.po.loan.IncomeRecord;
import com.bjpowernode.p2p.po.loan.LoanInfo;
import com.bjpowernode.p2p.po.user.FinanceAccount;
import com.bjpowernode.p2p.po.user.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@SpringBootTest
class ApplicationTests {

    @Resource
    private UserMapper userMapper;

    @Resource
    private FinanceAccountMapper financeAccountMapper;

    @Resource
    private BidInfoMapper bidInfoMapper;

    @Resource
    private LoanInfoMapper loanInfoMapper;

    @Resource
    private IncomeRecordMapper incomeRecordMapper;

    @Test
    void contextLoads() {
        String phone = "17339697120";
        String password = "123456";
        User user = new User();
        user.setPhone(phone);
        user.setLoginPassword(password);
        user.setAddTime(new Date());
        int userId = userMapper.insertUserReturnId(user);
        if (userId > 0){
            //根据返回的userId注册用户的金额表,初始金额为888.00
            Integer id = userMapper.selectUserByPhone(phone).getId();
            FinanceAccount financeAccount = new FinanceAccount();
            financeAccount.setUid(id);
            financeAccount.setAvailableMoney(BigDecimal.valueOf(888.00));
            financeAccountMapper.insert(financeAccount);
            //注册后根据phone查询用户并返回
            user = userMapper.selectByPrimaryKey(id);
            System.out.println(id);
        }

    }


    @Test
    public void test01(){
        BidInfo bidInfo = new BidInfo();
        bidInfo.setLoanId(1);
        bidInfo.setUid(2);
        bidInfo.setBidMoney(BigDecimal.valueOf(10));
        bidInfo.setBidTime(new Date());
        bidInfo.setBidStatus(1);
        int nums = bidInfoMapper.insertSelective(bidInfo);
        System.out.println(bidInfo.getId());
    }

    @Test
    public  void test02(){
        List<LoanInfo> loanInfos = loanInfoMapper.selectAllLoanInfo();
        for (LoanInfo loanInfo : loanInfos) {
            System.out.println(loanInfo);
        }
    }

    @Test
    public void test03(){
        List<IncomeRecord> incomeRecords = incomeRecordMapper.selectAllIncomeByLoanId(6);
        for (IncomeRecord incomeRecord : incomeRecords) {
            System.out.println(incomeRecord);
        }
    }

    @Test
    public void test04(){
        int i = incomeRecordMapper.updataByLoanId(6, new Date(), 2);
        System.out.println(i);
    }

    @Test
    public void test05(){
        int i = incomeRecordMapper.updataLoanOfStatus(6, 2,0);
        System.out.println(i);
    }

    @Test
    public void test06(){
        List<IncomeRecord> incomeRecords = incomeRecordMapper.selectIncomeByStatus(0);
        for (IncomeRecord incomeRecord : incomeRecords) {
            System.out.println(incomeRecord);
        }
    }

}
