package com.qtt.study.pay.controller;

import com.alibaba.fastjson.JSONObject;
import com.qtt.study.pay.entity.AlipayVo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ellie
 * @date 2021-06-11 下午2:06
 */
@RestController
public class AlipayController {

    String alipayUrl = "https://openapi.alipay.com/gateway.do";

    @PostMapping(value = "/pay")
    public String pay(@RequestBody AlipayVo alipayVo) {
        String s = JSONObject.toJSONString(alipayVo);
        System.out.println(s);
        return null;
    }
}
