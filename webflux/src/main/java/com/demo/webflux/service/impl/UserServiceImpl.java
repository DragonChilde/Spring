package com.demo.webflux.service.impl;

import com.demo.webflux.entity.User;
import com.demo.webflux.service.UserService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

/**
 * @title: UserServiceImpl
 * @Author Wen
 * @Date: 2021/6/8 14:59
 * @Version 1.0
 */
@Service
public class UserServiceImpl implements UserService {

    /**
     * 创建一个Map集合,存储模拟数据
     */
    private final Map<Integer, User> users = new HashMap<>();


    public UserServiceImpl() {

        users.put(1, new User("Tom", "man", 22));
        users.put(2, new User("Jack", "man", 30));
        users.put(3, new User("Lucy", "woman", 10));
    }

    @Override
    public Mono<User> getUserById(int id) {
        return Mono.justOrEmpty(users.get(id));
    }

    @Override
    public Flux<User> getUser() {
        return Flux.fromIterable(users.values());
    }

    @Override
    public Mono<Void> saveUserInfo(Mono<User> userMono) {
        //doOnNext这里类似遍历取值,里面是lambda表达式
        return userMono.doOnNext(user -> {
            //向map集合里放值
            int id = users.size() + 1;
            users.put(id, user);
        }).thenEmpty(Mono.empty()); //取完值后清空Mono,这就好比终止信号,不终止就是无限流
    }
}