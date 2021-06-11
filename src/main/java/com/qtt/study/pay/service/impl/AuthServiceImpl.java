package com.qtt.study.pay.service.impl;

import com.qtt.study.pay.entity.LoginVo;
import com.qtt.study.pay.entity.Result;
import com.qtt.study.pay.service.AuthService;
import com.qtt.study.pay.util.JwtUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @author Ellie
 * @date 2021-06-11 下午5:08
 */
@Service
public class AuthServiceImpl implements AuthService {
    @Override
    public String login(LoginVo loginVo) {
        String mobile = loginVo.getMobile();
        String password = loginVo.getPassword();
        if (StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password)) {
            System.out.println("登录失败");
        }
        String jwtToken = JwtUtils.getJwtToken(mobile, password);
        return jwtToken;
    }

    @Override
    public void register(LoginVo loginVo) {

    }
}
