package com.demo.demoreactor.reactor;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @title: TestReactor
 * @Author Wen
 * @Date: 2021/6/8 9:57
 * @Version 1.0
 */
public class TestReactor {

    public static void main(String[] args) {
        //just 方法直接声明
        Flux.just(1, 2, 3, 4).subscribe(System.out::println);
        Mono.just(1).subscribe(System.out::println);

        //其它的方法
        Integer[] array = {1, 2, 3, 4};
        Flux.fromArray(array);

        List<Integer> list = Arrays.asList(array);
        Flux.fromIterable(list);

        Stream<Integer> stream = list.stream();
        Flux.fromStream(stream);
    }
}