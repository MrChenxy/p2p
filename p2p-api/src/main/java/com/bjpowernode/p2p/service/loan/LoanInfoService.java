package com.bjpowernode.p2p.service.loan;


import com.bjpowernode.p2p.po.loan.LoanInfo;
import com.github.pagehelper.PageInfo;
import java.math.BigDecimal;
import java.util.List;

public interface LoanInfoService {

    /**
     * 计算产品历史年华收益率
     * @return BigDecimal
     */
    BigDecimal queryHistoryAvgRate();

    /**
     * 按产品类型，分页查询， Order by Rate desc
     * @return
     */
    List<LoanInfo> queryLoanInfoByProductTypePage(Integer productType,
                                                  Integer pageNo,
                                                  Integer pageSize);
    PageInfo queryLoanInfoByTypeAndPage(Integer productType,
                                        Integer pageNo,
                                        Integer pageSize);
    /**
     * 按发布时间分页查询产品，按Order by release_time desc
     * @param productType
     * @param pageNo
     * @param pageSize
     * @return
     */
    List<LoanInfo> queryLoanInfoListByProductType(Integer productType, Integer pageNo, Integer pageSize);

    /**
     * 按产品类型作为条件，查询总记录数
     * @return
     */
    int countRowByProductType(Integer productType);


    /**
     * 按产品表主键查询产品
     * @param loanId 产品id
     * @return
     */
    LoanInfo queryLoanInfoById(Integer loanId);

    /**
     * 根据loanId更新left_money
     * @param loanId
     * @param ver
     * @param money
     * @return
     */
    int updateLoanInfoOfMoney(Integer loanId,Integer ver,BigDecimal money);

    int updateLoanInfoById(LoanInfo loanInfo);
}
