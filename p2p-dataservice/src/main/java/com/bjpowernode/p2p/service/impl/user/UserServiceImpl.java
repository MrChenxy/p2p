package com.bjpowernode.p2p.service.impl.user;


import com.alibaba.dubbo.config.annotation.Service;
import com.bjpowernode.p2p.mapper.user.FinanceAccountMapper;
import com.bjpowernode.p2p.mapper.user.UserMapper;
import com.bjpowernode.p2p.po.user.FinanceAccount;
import com.bjpowernode.p2p.po.user.User;
import com.bjpowernode.p2p.service.user.UserService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 *@author Mr.chenxy
 *@date 2021/3/2
 */
@Component
@Service(interfaceClass = UserService.class,version = "1.0.0",timeout = 3500)
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private FinanceAccountMapper financeAccountMapper;


    @Override
    public int sumTotalUser() {
        return userMapper.countTotalUser();
    }

    @Override
    public User queryPhone(String phone) {
        User user = userMapper.selectUserByPhone(phone);
        return user;
    }

    @Override
    @Transactional
    public User register(String phone, String password) {
        //注册user账户
        User user = new User();
        user.setPhone(phone);
        user.setLoginPassword(password);
        user.setAddTime(new Date());
        int num = userMapper.insertUserReturnId(user);
        if (num > 0){
            //根据返回的userId注册用户的金额表,初始金额为888.00
            Integer id = userMapper.selectUserByPhone(phone).getId();
            FinanceAccount financeAccount = new FinanceAccount();
            financeAccount.setUid(id);
            financeAccount.setAvailableMoney(BigDecimal.valueOf(888.00));
            financeAccountMapper.insert(financeAccount);
            //注册后根据phone查询用户并返回
            user = userMapper.selectByPrimaryKey(id);
            return user;
        }
        return null;
    }

    @Override
    public int modifyUser(User user) {
        int num = userMapper.updateUserByPhone(user);
        return num;
    }

    @Override
    public int modifyLoginTime(User user) {
        int num = userMapper.updateLoginTimeByPhone(user);
        return num;
    }

    @Override
    public User login(String phone, String pwd) {
        return null;
    }
}
