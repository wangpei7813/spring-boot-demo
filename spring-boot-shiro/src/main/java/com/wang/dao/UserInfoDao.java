package com.wang.dao;


import com.wang.model.UserInfo;

public interface UserInfoDao {
    /**通过username查找用户信息;*/
    UserInfo findByUsername(String username);
}