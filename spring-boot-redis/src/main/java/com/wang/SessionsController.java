package com.wang;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * session 共享
 */
@RestController
@RequestMapping("/sessions")
public class SessionsController {

    @Resource
    private RedisUtil redisUtil;

    @GetMapping("/set")
    public Map<String, Object> setSession(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        request.getSession().setAttribute("request Url", request.getRequestURL());
        map.put("request Url", request.getRequestURL());
        return map;
    }

    @GetMapping(value = "/list")
    public Object sessions (HttpServletRequest request){
        Map<String, Object> map = new HashMap<>();
        map.put("sessionId", request.getSession().getId());
        map.put("message", request.getSession().getAttribute("request Url"));
        return map;
    }

    @GetMapping("set-value")
    public void set() {
        redisUtil.set("test_key","test_value");
    }

    @GetMapping("get-value")
    public String getValue() {
        return (String) redisUtil.get("test_key");
    }
}
