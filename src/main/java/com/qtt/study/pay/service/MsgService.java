package com.qtt.study.pay.service;

import java.util.HashMap;

/**
 * @author Ellie
 * @date 2021-06-11 下午4:08
 */
public interface MsgService {
    boolean send(HashMap<String, Object> hashMap, String phone);
}
