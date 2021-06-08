package com.demo.webflux.Handler;

import com.demo.webflux.entity.User;
import com.demo.webflux.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @title: UserHandler
 * @Author Wen
 * @Date: 2021/6/8 16:37
 * @Version 1.0
 */
public class UserHandler {

    private final UserService userService;

    public UserHandler(UserService userService) {
        this.userService = userService;
    }

    /**
     * 根据id查询
     *
     * @param request
     * @return
     */
    public Mono<ServerResponse> getUserById(ServerRequest request) {
        /**
         * 获取id值
         */
        int userId = Integer.parseInt(request.pathVariable("id"));

        /**
         * 空值处理
         */
        Mono<ServerResponse> notFound = ServerResponse.notFound().build();

        /**
         * 调用service方法得到数据
         */
        Mono<User> userMono = userService.getUserById(userId);

        /**
         * 把userMono进行转换返回,使用Reactor操作符flatMap
         */
        return userMono.flatMap(user -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromValue(user)))
                .switchIfEmpty(notFound);


    }

    /**
     * 查询所有
     *
     * @param request 这里request用不上也必须要写进来,不然添加Route无法指定映射
     * @return
     */
    public Mono<ServerResponse> getAllUsers(ServerRequest request) {
        /**
         * 调用service得到结果
         */
        Flux<User> users = userService.getUser();
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(users, User.class);
    }

    public Mono<ServerResponse> saveUser(ServerRequest request) {
        //得到user对象
        Mono<User> userMono = request.bodyToMono(User.class);
        return ServerResponse.ok().build(userService.saveUserInfo(userMono));
    }
}