package com.wang.interceptor;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @Author wp
 * @date 2018/11/30 17:46
 */
public class AuthFilter extends AuthenticatingFilter {
    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        System.out.println("----------------------------executeLogin-------------------------");
        SecurityUtils.getSubject().logout();
        return super.executeLogin(request, response);
    }

    @Override
    protected AuthenticationToken createToken(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        return null;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        return false;
    }
}
