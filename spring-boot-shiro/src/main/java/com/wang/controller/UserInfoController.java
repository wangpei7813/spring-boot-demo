package com.wang.controller;

import com.alibaba.fastjson.JSON;
import com.wang.model.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/userInfo")
@Slf4j
public class UserInfoController {


    /**
     * 用户查询.
     *
     * @return
     */
    @RequestMapping("/userList")
//    @RequiresPermissions("userInfo:view")//权限管理;
    public String userInfo() {
//        SecurityUtils.setSecurityManager(sm);
        Subject subject = SecurityUtils.getSubject();
        UserInfo userInfo = (UserInfo) subject.getPrincipal();
//        UserInfo userInfo = new UserInfo();
//        BeanUtils.copyProperties(loginUser, userInfo);
        log.info(JSON.toJSONString(userInfo));
        return "user_info";
    }

    /**
     * 用户添加;
     *
     * @return
     */
    @RequestMapping("/userAdd")
    @RequiresPermissions("userInfo:add")//权限管理;
    public String userInfoAdd() {
        return "userInfoAdd";
    }

    /**
     * 用户删除;
     *
     * @return
     */
    @RequestMapping("/userDel")
    @RequiresPermissions("userInfo:del")//权限管理;
    public String userDel() {
        return "userInfoDel";
    }
}