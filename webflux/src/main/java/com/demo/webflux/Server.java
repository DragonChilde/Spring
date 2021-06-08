package com.demo.webflux;

import com.demo.webflux.Handler.UserHandler;
import com.demo.webflux.service.UserService;
import com.demo.webflux.service.impl.UserServiceImpl;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.http.server.reactive.ReactorHttpHandlerAdapter;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.netty.http.server.HttpServer;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.toHttpHandler;

/**
 * @title: Server
 * @Author Wen
 * @Date: 2021/6/8 17:13
 * @Version 1.0
 */
public class Server {

    public static void main(String[] args) throws Exception {
        Server server = new Server();
        server.createReactorServer();
        System.out.println("ok");
        System.in.read();

    }

    /**
     * 1. 创建Router路由
     *
     * @return
     */
    public RouterFunction<ServerResponse> routingFunction() {
        /**
         * 创建Handler对象
         */
        UserService userService = new UserServiceImpl();
        UserHandler userHandler = new UserHandler(userService);

        /**
         * 设置路由
         */
        return RouterFunctions.route(GET("/users/{id}").and(accept(MediaType.APPLICATION_JSON)), userHandler::getUserById)
                .andRoute(GET("/users").and(accept(MediaType.APPLICATION_JSON)), userHandler::getAllUsers);
    }

    /**
     * 创建服务器完成适配
     */
    public void createReactorServer() {

        /**
         * 路由和handler适配
         */
        RouterFunction<ServerResponse> route = routingFunction();
        HttpHandler httpHandler = toHttpHandler(route);
        ReactorHttpHandlerAdapter adapter = new ReactorHttpHandlerAdapter(httpHandler);
        /**
         * 创建服务器
         */
        HttpServer httpServer = HttpServer.create();
        httpServer.handle(adapter).bindNow();
    }

}