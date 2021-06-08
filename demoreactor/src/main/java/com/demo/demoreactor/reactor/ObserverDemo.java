package com.demo.demoreactor.reactor;

import java.util.Observable;
import java.util.Observer;

/**
 * @title: ObserverDemo
 * @Author Wen
 * @Date: 2021/6/7 17:15
 * @Version 1.0
 */
public class ObserverDemo extends Observable {

    public static void main(String[] args) {
        ObserverDemo observer = new ObserverDemo();
        //添加观察者
        observer.addObserver(
                (o, arg) -> {
                    System.out.println("change!");
                });

        observer.addObserver((o, arg) -> {
            System.out.println("hand observer notify,ready change!");
        });

        /**
         * 这里的实际作用就是上面检测到数据有变化,要进行数据变化通知,不然观察者并不知道数据有所变化
         */
        observer.setChanged();  //数据变化
        observer.notifyObservers();//通知

        /*
        hand observer notify,ready change!
        change
        */
    }
}