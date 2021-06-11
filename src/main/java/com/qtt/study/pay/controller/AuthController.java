package com.qtt.study.pay.controller;

import com.qtt.study.pay.entity.LoginVo;
import com.qtt.study.pay.entity.Result;
import com.qtt.study.pay.service.AuthService;
import com.qtt.study.pay.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Ellie
 * @date 2021-06-11 下午3:10
 */
@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public Result login(@RequestBody LoginVo loginVo) {
        String token = authService.login(loginVo);
        return new Result().ok(token);
    }

    @GetMapping(value = "/parse")
    public Result parse(HttpServletRequest request) {
        String mobile = JwtUtils.getInfoByJwtToken(request, "mobile");
        String password = JwtUtils.getInfoByJwtToken(request,"password");
        return new Result().ok(mobile + "---" + password);
    }

    @PostMapping(value = "/register")
    public Result register(@RequestBody LoginVo loginVo) {
        authService.register(loginVo);
        return new Result().ok("success");
    }
}
