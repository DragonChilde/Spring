package com.demo.webflux.service;

import com.demo.webflux.entity.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @title: UserService
 * @Author Wen
 * @Date: 2021/6/8 14:56
 * @Version 1.0
 */
public interface UserService {

    /**
     * 根据id查询用户
     *
     * @param id 主键
     * @return 指定id的用户
     */
    Mono<User> getUserById(int id);


    /**
     * 查询所有用户
     *
     * @return 用户集合
     */
    Flux<User> getUser();


    /**
     * 添加用户
     *
     * @param user 添加的用户
     * @return 无返回值
     */
    Mono<Void> saveUserInfo(Mono<User> user);
}
