package com.bjpowernode.p2p.common;

public class Constants {


    //session中的key，表示用户
    public static final String SESSION_USER = "user";

    //金额的常量值
    public static final String AVAIABLE_MONEY="avaiableMoney";

    //常量类 ctrl + shift + u
    // 历史年华收益率
    public static final String HISTORY_AVG_RATE="historyAvgRate";
    // 平台注册用户数量
    public static final String USER_TOTAL_NUMBERS = "totalUserNumbers";
    // 平台累计投资金额
    public static final String TATAL_BID_MONEY = "totalBidMoney" ;


    //产品类型
    //新手宝产品类型
    public static final Integer X_LOANINFO_PRODUCT_TYPE = 0;
    //优选
    public static final Integer Y_LOANINFO_PRODUCT_TYPE = 1;
    //散标
    public static final Integer S_LOANINFO_PRODUCT_TYPE = 2;


    //产品状态 未满标
    public static final Integer LOAN_STATUS_NOT_ENOUTH =  0;
    //满标
    public static final Integer LOAN_STATUS_ENOUTH =  1;
    // 满标生成收益计划
    public static final Integer LOAN_STATUS_INCOME_PLAN =  2;


    //充值状态
    public static final Integer RECHARGE_START = 0;
    //充值成功
    public static final Integer RECHARGE_SUCCESS = 1;
    // 充值失败
    public static final Integer RECHARGE_FAIL = 2;



    //分页
    public static final Integer PAGE_DEFAULT_PAGESIZE = 9;

    /**
     * 京东万象
     */
    //访问106短信的url
    public static final String JDWX_106SMS_URL="https://way.jd.com/kaixintong/kaixintong";
    //京东万象的key
    public static final String JDWX_APPKEY="3680fa919b771148da626bbcbd459475";
    //短信的模版
    public static final String JDWX_SMS_CONTENT="【凯信通】您的验证码是：%s";

    //实名认证
    public static final String JDWX_IDCARD_URL="https://way.jd.com/youhuoBeijing/test";



    /**
     * redis 的 key 定义
     */
    //发送短信验证码的key SMS:CODE:1350000000
    public static final String KEY_REDIS_SMS = "SMS:CODE:" ;

    public static final Object INVEST_TOP = "INVERT:TOP";

    //支付宝支付， 生成的唯一订单号使用的
    public static final String ALIPAY_TRANDE_NO_NUMBER = "PAY:ALIPAY:TRADENO" ;
}
