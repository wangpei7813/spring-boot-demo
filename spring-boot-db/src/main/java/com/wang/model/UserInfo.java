package com.wang.model;

import lombok.Data;


/**
 * @Author wp
 * @date 2018/11/21 9:51
 */
@Data
public class UserInfo {
    private Long id;
    private int age;
    private int sex;
    private String password;
    private String username;
}
