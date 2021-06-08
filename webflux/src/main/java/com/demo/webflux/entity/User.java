package com.demo.webflux.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @title: User实体类
 * @Author Wen
 * @Date: 2021/6/8 14:54
 * @Version 1.0
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {

    private String name;
    private String gender;
    private Integer age;
}