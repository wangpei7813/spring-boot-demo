package com.wang.service;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author wp
 * @date 2018/12/3 10:31
 */
@Component
public class HelloSender {
    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send() {
        String context = "hello " + new Date();
        System.out.println("Sender : " + context);
        this.rabbitTemplate.convertAndSend("hello", context);
    }

    public void sendTopic() {
        String context = "hi, i am message 1";
        HashMap<String, Object> map = new HashMap<>();
        map.put("msg", "123ewqdsaddfasf");
        map.put("context",context);
        this.rabbitTemplate.convertAndSend("exchange", "topic.message", map);
    }

    public void sendTopic2() {
        String context = "hi, i am messages 2";
        System.out.println("sendTopic2 : " + context);
        this.rabbitTemplate.convertAndSend("exchange", "topic.messages", context);
    }
}
