package com.bjpowernode.p2p.impl.user;/**
 * @author gsyzh
 * @create 2021-03-02 20:32
 */

import com.alibaba.dubbo.config.annotation.Service;
import com.bjpowernode.p2p.po.user.User;
import com.bjpowernode.p2p.service.user.UserService;
import org.springframework.stereotype.Component;

/**
 *
 *@author Mr.chenxy
 *@date 2021/3/2
 */
@Component
@Service(interfaceClass = UserService.class,version = "1.0.0",timeout = 3500)
public class UserServiceImpl implements UserService {

    @Override
    public int sumTotalUser() {
        return 10086;
    }

    @Override
    public User queryPhone(String phone) {
        return null;
    }

    @Override
    public User register(String phone, String password) {
        return null;
    }

    @Override
    public int modifyUser(User user) {
        return 0;
    }

    @Override
    public User login(String phone, String pwd) {
        return null;
    }
}
