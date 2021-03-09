package com.bjpowernode.p2p.task;

import com.alibaba.dubbo.config.annotation.Reference;
import com.bjpowernode.p2p.service.loan.IncomeService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


/**
 * @author Mr.chenxy
 * @date 2021/3/9
 */
@Component
public class TaskManager {

    @Reference(interfaceClass = IncomeService.class,version = "1.0.0",timeout = 3500)
    private IncomeService incomeService;

    //定时任务都是无参数,无返回值,公共的
    //@Scheduled(cron = "*/5 * * * * ?")
    public void test01(){
        //System.out.println(new Date());
    }

    /**
     * 收益定时任务
     */
    @Scheduled(cron = "*/10 * * * * ?")
    public void getGenerateIncomeBack(){
        incomeService.generateIncomeBack();
    }
}
