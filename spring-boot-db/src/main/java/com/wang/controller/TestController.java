package com.wang.controller;

import com.wang.service.UserServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author wp
 * @date 2018/11/21 9:59
 */
@RestController
public class TestController {

    @Resource
    private UserServiceImpl userService;

    @RequestMapping("/test-db")
    public String testDB() {
        userService.testDB();
        return "";
    }
}
