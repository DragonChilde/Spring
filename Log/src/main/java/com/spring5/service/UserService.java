package com.spring5.service;

import org.springframework.stereotype.Service;

/**
 * @title: UserService
 * @Author Wen
 * @Date: 2021/6/7 15:20
 * @Version 1.0
 */
@Service
public class UserService {

    public void testAbnormal() {
        int i = 10 / 0;
    }
}