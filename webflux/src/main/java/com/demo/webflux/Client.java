package com.demo.webflux;

import com.demo.webflux.entity.User;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

/**
 * @title: Client
 * @Author Wen
 * @Date: 2021/6/8 17:46
 * @Version 1.0
 */
public class Client {

    public static void main(String[] args) {
        /**
         * 调用服务器地址
         */
        WebClient webClient = WebClient.create("http://localhost:52240");

        /**
         * 根据ID查询
         */
        User response = webClient.get()
                .uri("/users/{id}", 1)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(User.class)
                .block();

        System.out.println(response.getName());


        /**
         * 查询所有
         */

        Flux<User> results = webClient.get()
                .uri("/users")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(User.class);

        results.map(user -> user.getName())
                .buffer()   //缓冲
                .doOnNext(System.out::println)  //遍历输出
                .blockFirst();  //订阅
    }
}