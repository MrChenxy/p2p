package com.bjpowernode.p2p.mapper.user;


import com.bjpowernode.p2p.po.user.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {

    /**自定义方法开始*/

    /**
     * 计算平台的总用户数
     * @retun int
     */
    int countTotalUser();


    /**
     * 按手机号查询User
     * @param phone  手机号
     * @return
     */
    User selectUserByPhone(@Param("phone") String phone);


    /**
     * 注册用户，同时能返回注册的记录主键id值。
     * 要求：主键是自动自动增长的
     * @param record
     * @return
     */
    int insertUserReturnId(User record);


    /**
     * 以手机号为条件更新用户信息
     * @param user
     * @return
     */
    int updateUserByPhone(User user);

    /**自定义方法完成*/
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);


}