package com.qtt.study.pay.service;

import com.qtt.study.pay.entity.LoginVo;

/**
 * @author Ellie
 * @date 2021-06-11 下午5:07
 */
public interface AuthService {
    String login(LoginVo loginVo);

    void register(LoginVo loginVo);
}
