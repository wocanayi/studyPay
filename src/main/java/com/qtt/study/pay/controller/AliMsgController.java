package com.qtt.study.pay.controller;

import com.qtt.study.pay.entity.Result;
import com.qtt.study.pay.service.MsgService;
import com.qtt.study.pay.util.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author Ellie
 * @date 2021-06-11 下午4:00
 */
@RestController
public class AliMsgController {

    @Autowired
    private MsgService msgService;

    //@Autowired
    //private RedisTemplate<String, String> redisTemplate;

    @GetMapping(value = "/send/{phone}")
    public Result sendMsg(@PathVariable String phone) {
        // 先从redis中取验证码取到直接返回
        //String code = redisTemplate.opsForValue().get(phone);
        //if (!StringUtils.isEmpty(code)) {
        //    return new Result().ok(code);
        //}
        // 取不到再发短信
        String code = RandomUtils.generateMixString(6);
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("code", code);
        boolean isSend = msgService.send(hashMap, phone);
        if (isSend) {
            // 发送成功将验证码放到redis中，并设置失效时间
            //redisTemplate.opsForValue().set(phone, code, 5, TimeUnit.MINUTES);
            return new Result(2, "发送短信成功", code);
        } else {
            return new Result(5, "发送短信失败");
        }
    }
}
