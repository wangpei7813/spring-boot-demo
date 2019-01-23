package com.wang.nettyrpc.common;

import lombok.Data;

import java.io.Serializable;

/**
 * 消息传递的实体类
 * @author wp
 * @date 2019/1/22 10:35
 */
@Data
public class ClassInfo implements Serializable{

    private String className;//类名
    private String methodName;//函数名称
    private Class<?>[] types;//参数类型
    private Object[] objects;//参数列表
}
