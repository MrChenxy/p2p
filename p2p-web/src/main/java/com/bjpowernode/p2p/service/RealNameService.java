package com.bjpowernode.p2p.service;

import com.alibaba.fastjson.JSONObject;
import com.bjpowernode.p2p.util.HttpClientUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Mr.chenxy
 * @date 2021/3/7
 * 验证用户信息service
 */
@Service
public class RealNameService {

    public boolean checkName(String realName, String idCard){
        boolean flag = false;
        try {
            String param = "name="+realName+"&certNo=" + idCard + "&appkey=5961ec1511db397e31be684542ee13f0";
            String uri = "https://way.jd.com/YOUYU365/jd_credit_two?"+param;
            //使用httpclient发送姓名和身份证信息进行验证, 并获取返回的信息
            String str = HttpClientUtils.doGet(uri);
            //把返回的数据转换成json,并解析
            JSONObject jsonObj = JSONObject.parseObject(str);
            if (jsonObj != null){
                String code = jsonObj.getString("code");
                if ("10000".equals(code)){
                    String resultObj = jsonObj.getString("result");
                    JSONObject obj = JSONObject.parseObject(resultObj);
                    Object success = obj.get("success");
                    if ("true".equals(success)){
                        flag = true;
                        return flag;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }




}
