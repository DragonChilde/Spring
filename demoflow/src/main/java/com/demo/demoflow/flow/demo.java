package com.demo.demoflow.flow;

import java.util.concurrent.Flow;

/**
 * @title: demo
 * @Author Wen
 * @Date: 2021/6/7 17:34
 * @Version 1.0
 */
public class demo {

    public static void main(String[] args) {

        /**
         * Publisher表示发布,这里必使用的是onNext和subscriber,subscriber表示的是订阅
         * Publisher在没有订阅的时候,是不会触发任何的行为
         * 而onNext就是在发送信号,订阅者收到信号后再作相应的处理
         */
        Flow.Publisher<String> publisher = subscriber -> {
            subscriber.onNext("1");
            subscriber.onNext("2");
            subscriber.onError(new RuntimeException("error"));
        };


        publisher.subscribe(new Flow.Subscriber<String>() {
            @Override
            public void onSubscribe(Flow.Subscription subscription) {
                subscription.cancel();
            }

            @Override
            public void onNext(String item) {

                System.out.println(item);
            }

            @Override
            public void onError(Throwable throwable) {

                System.out.println("error");
            }

            @Override
            public void onComplete() {

                System.out.println("publish complete!");
            }
        });

        /*
        1
        2
        error
        */
    }
}