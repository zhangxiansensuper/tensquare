package com.tensquare.sms.listener;

import com.tensquare.sms.utils.SendSmsUtil;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Author zhang
 * @Date 2020/5/10 11:13
 * @Version 1.0
 */
@Component
@RabbitListener(queues = "sms")
public class SmsListener {

    @Autowired
    private SendSmsUtil sendSmsUtil;

    //模板编号
    @Value("${aliyun.sms.template_code}")
    private String template_code;

    //签名
    @Value("${aliyun.sms.sign_name}")
    private String sign_name;

    /**
     * 发送短信
     * @param map
     */
    @RabbitHandler
    public void sendMsg(Map<String,String> map){
        String mobile = map.get("mobile");
        String checkcode = map.get("checkcode");
        System.out.println("手机号："+mobile);
        System.out.println("验证码："+checkcode);

        try {
            sendSmsUtil.sendMessage(mobile,template_code,sign_name,
                    "{\"number\":\""+ checkcode +"\"}");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
