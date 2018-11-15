package com.wang.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.NamedThreadLocal;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
public class ExcuteTimeInterceptor extends HandlerInterceptorAdapter {

    private NamedThreadLocal<Date> startTimeThreadLocal = new NamedThreadLocal<Date>("StopWatch-StartTime");
    private static DateFormat sdf = new SimpleDateFormat("yyyyMMdd-HH:mm:ss:SSS");

    @Override
    public boolean preHandle(HttpServletRequest rquest, HttpServletResponse response, Object obj) throws Exception {
        //线程绑定变量（该数据只有当前请求的线程可见）
        startTimeThreadLocal.set(new Date());
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object obj, Exception exception) throws Exception {
        //2、结束时间
        Date endTime = new Date();
        //得到线程绑定的局部变量（开始时间）
        Date beginTime = startTimeThreadLocal.get();
        //3、消耗的时间
        long consumeTime = endTime.getTime() - beginTime.getTime();
        log.info("请求:" + request.getRequestURI() + "，请求处理开始时间：" + sdf.format(beginTime) + "，请求处理结束时间：" + sdf.format(endTime)
                + "，耗时：" + consumeTime + "毫秒");
    }
}
