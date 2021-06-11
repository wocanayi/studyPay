package com.qtt.study.pay.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.qtt.study.pay.service.MsgService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;

/**
 * @author Ellie
 * @date 2021-06-11 下午4:15
 */
@Service
public class MsgServiceImpl implements MsgService {

    @Override
    public boolean send(HashMap<String, Object> hashMap, String phone) {
        if (StringUtils.isEmpty(phone)) {
            return false;
        }
        DefaultProfile profile = DefaultProfile.getProfile("default", "LTAI4FvvVEWiTJ3GNJJqJnk7", "9st82dv7EvFk9mTjYO1XXbM632fRbG");
        DefaultAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("PhoneNumbers", phone);
        request.putQueryParameter("SignName", "我的谷粒在线教育网站");
        request.putQueryParameter("TemplateCode", "SMS_180051135");
        request.putQueryParameter("TemplateParam", JSONObject.toJSONString(hashMap));
        try {
            CommonResponse response = client.getCommonResponse(request);
            boolean isSuccess = response.getHttpResponse().isSuccess();
            return isSuccess;
        } catch (ClientException e) {
            e.printStackTrace();
            return false;
        }
    }
}
