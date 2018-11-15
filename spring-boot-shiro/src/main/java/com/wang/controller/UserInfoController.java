package com.wang.controller;

import com.alibaba.fastjson.JSON;
import com.wang.common.RedisKey;
import com.wang.common.RedisUtil;
import com.wang.model.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Controller
@RequestMapping("/userInfo")
@Slf4j
public class UserInfoController {

    @Resource
    private RedisUtil redisUtil;


    @ResponseBody
    @RequestMapping("get-user-info")
    public UserInfo getUserInfo(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        String sessionId = "";
        for (Cookie cookie : cookies) {
            if (Objects.equals(cookie.getName(), "JSESSIONID")) {
                sessionId = cookie.getValue();
            }
        }
        Session session = (Session) redisUtil.get(RedisKey.SESSION_KEY + sessionId);
        return (UserInfo) session.getAttribute("LOGIN_USER");
    }


    /**
     * 用户查询.
     *
     * @return
     */
    @RequestMapping("/userList")
    @RequiresPermissions("userInfo:view")//权限管理;
    public String userInfo() {
        Subject subject = SecurityUtils.getSubject();
        UserInfo userInfo = (UserInfo) subject.getPrincipal();
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