package com.bjpowernode.p2p.service;

import com.alibaba.fastjson.JSONObject;
import com.bjpowernode.p2p.common.Constants;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Node;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * @author Mr.chenxy
 * @date 2021/3/6
 * 发送信息service
 */
@Service
public class SendCodeService {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 对位发送验证码方法, 并把验证码存进redis
     * @param phone
     * @return
     */
    public boolean sendSmsCode(String phone){
        boolean flags = false;
        String code = VerCode();
        flags = sendCode(phone, code);
        if (flags == true){
            stringRedisTemplate.opsForValue().set(Constants.KEY_REDIS_SMS+phone,code,3, TimeUnit.MINUTES);
            return flags;
        }
        return flags;
    }

    /**
     * 使用httpclient调用发送短信接口
     */
    private boolean sendCode(String phone, String code){
        boolean flag = false;
        try {
            String param = "mobile="+phone+"&content=【创信】您的验证码是：" + code + "，3分钟内有效！&appkey=5961ec1511db397e31be684542ee13f0";
            String uri = "https://way.jd.com/chuangxin/dxjk?"+param;
            //通过工具类发送get请求并获取响应结果
            //String str = HttpClientUtils.doGet(uri);

            //使用固定的返回成功的str
            String str = Constants.HTTPCLIENT_RESPONSE_STR;
           //解析响应的json格式字符串
            JSONObject jsonObj = JSONObject.parseObject(str);
            if (jsonObj != null){
                String codeValue = jsonObj.getString("code");
                if ("10000".equals(codeValue)){
                    String resultXml = jsonObj.getString("result");
                    if (resultXml != null && resultXml != ""){
                        //解析xml文件,取出其中的returnStatus值,如果是success则证明信息发送成功
                        String resultStatus = doXml(resultXml);
                        if ("Success".equalsIgnoreCase(resultStatus)){
                            flag = true;
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 解析xml文件
     */
    private String doXml(String xml){
        String returnStatus = "";
        try {
            //获取xml的document对象
            Document document = DocumentHelper.parseText(xml);
            Node node = document.selectSingleNode("//returnstatus");
            if (node != null){
                returnStatus = node.getText();
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return returnStatus;
    }


    /**
     * 随机生成6位验证码
     */
    private static String VerCode(){
        StringBuffer code = new StringBuffer("");
        for (int i = 0; i < 6; i++) {
            code.append(ThreadLocalRandom.current().nextInt(0, 10));
        }
        return code.toString();
    }

    /**
     * 验证输入的验证码是否正确
     * @param phone
     * @param code
     * @return
     */
    public boolean checkCode(String phone,String code){
        boolean flag = false;
        String resultCode = stringRedisTemplate.opsForValue().get(Constants.KEY_REDIS_SMS + phone);
        if (resultCode != null && code.equals(resultCode)){
            stringRedisTemplate.opsForValue().set(Constants.KEY_REDIS_SMS + phone,code,5, TimeUnit.SECONDS);
            flag = true;
            return flag;
        }
        return flag;
    }

}
