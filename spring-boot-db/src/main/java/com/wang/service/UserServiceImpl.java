package com.wang.service;

import com.wang.dao.master.UserMapper;
import com.wang.dao.slave.UserSlaveMapper;
import com.wang.model.UserInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @Author wp
 * @date 2018/11/21 10:00
 */
@Service
public class UserServiceImpl {

    @Resource
    private UserMapper master;
    @Resource
    private UserSlaveMapper slave;

    @Transactional
    public void testDB() {
        UserInfo userInfo = new UserInfo();
        userInfo.setId(2L);
        userInfo.setAge(23);
        slave.update(userInfo);
        userInfo = new UserInfo();
        userInfo.setId(1L);
        userInfo.setAge(26);
        master.update(userInfo);
    }
}
