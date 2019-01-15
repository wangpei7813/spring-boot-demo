package com.wang.guava;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author wp
 * @date 2019/1/12 15:56
 */
@RestController
@Slf4j
public class CacheController {

    @GetMapping("set")
    public void set() {
        LinkedBlockingDeque<Long> idQueue = IdGeneratorUtil.idQueue;

        for (int i = 0; i < 10; i++) {
            idQueue.offer(System.currentTimeMillis() + (i + 1));
        }

        IdGeneratorUtil.setCache(IdGeneratorUtil.ID_GENERATOR_KEY, idQueue);
    }


    @GetMapping("get")
    public String getAll() {



        return IdGeneratorUtil.get().toString();
    }
}
