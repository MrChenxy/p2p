package com.bjpowernode.p2p.service.user;

import com.bjpowernode.p2p.po.user.User;


public interface UserService {

    /**
     * 计算平台总用户数量
     * @return
     */
    int sumTotalUser();

    /**
     * 使用手机号查询User对象
     * @param phone  手机号
     * @return
     */
    User queryPhone(String phone);


    /**
     * 注册： 添加用户到 u_user, 同时创建 u_finance_account记录
     * @param phone
     * @param password
     * @return
     */
    User register(String phone, String password);


    /**
     * 更新用户
     * @param user
     * @return
     */
    int  modifyUser(User user);

    /**
     * 更新用户最后一次登录时间
     * @param user
     * @return
     */
    int modifyLoginTime(User user);

    /**
     * 登录
     * @param phone 手机号
     * @param pwd   md5加密过的密码
     * @return
     */
    User login(String phone, String pwd);
}
