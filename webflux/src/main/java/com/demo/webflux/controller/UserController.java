package com.demo.webflux.controller;

import com.demo.webflux.entity.User;
import com.demo.webflux.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @title: UserController
 * @Author Wen
 * @Date: 2021/6/8 15:22
 * @Version 1.0
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    @GetMapping("/user/{id}")
    public Mono<User> getUserId(@PathVariable int id) {
        return userService.getUserById(id);
    }


    @GetMapping("/user")
    public Flux<User> getUsers() {
        return userService.getUser();
    }

    @PostMapping("/saveuser")
    public Mono<Void> saveUser(@RequestBody User user) {
        Mono<User> userMono = Mono.just(user);
        return userService.saveUserInfo(userMono);
    }
}