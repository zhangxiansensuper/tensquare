package com.tensquare.sms.utils;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import org.springframework.stereotype.Component;

/**
 * @Author zhang
 * @Date 2020/5/10 16:33
 * @Version 1.0
 */
@Component
public class SendSmsUtil {

    DefaultProfile profile = DefaultProfile.getProfile("default", "LTAI41s7UdycZniy", "HngXYiuYNWWPfBjCbHNQZuwhqtwDjQ");
    IAcsClient client = new DefaultAcsClient(profile);

    public String sendMessage(String phoneNum,String template_code,String sign_name,String param){
        CommonRequest request = new CommonRequest();
        //request.setSysProtocol(ProtocolType.HTTPS);
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        //接受验证码的手机号
        request.putQueryParameter("phoneNum", phoneNum);
        //签名
        request.putQueryParameter("sign_name", sign_name);
        //模板代码
        request.putQueryParameter("TemplateCode", template_code);
        //用户定义的验证码内容
        request.putQueryParameter("TemplateParam", param);
        try {
            CommonResponse response = client.getCommonResponse(request);
            String returnstr = response.getData();
            System.out.println(returnstr);
            JSONObject returnjsonstr = JSONObject.parseObject(returnstr);
            return returnjsonstr.getString("Message");//返回的信息
        } catch (ServerException e) {
            return e.getErrMsg();
        } catch (ClientException e) {
            return e.getErrMsg();
        }
    }
}
