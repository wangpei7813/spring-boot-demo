package com.wang.guava;

import com.google.common.cache.*;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author wp
 * @date 2019/1/12 15:13
 */
@Slf4j
public class IdGeneratorUtil {

    public static int maxSize = 100000;

    public static final String ID_GENERATOR_KEY = "ID_GENERATOR_KEY";

    // 临界值
    public static int criticalValue = 40000;

    public static LinkedBlockingDeque<Long> idQueue = new LinkedBlockingDeque<>(maxSize);

    public static LoadingCache<String, Object> build = CacheBuilder
            .newBuilder()
//            .concurrencyLevel(5)
            .maximumSize(100)
            /*.removalListener(
                    new RemovalListener<String, Object>() {
                        @Override
                        public void onRemoval(RemovalNotification<String, Object> removeObj) {
                            System.out.println("------------" + removeObj.toString());
                            build.invalidate(removeObj.getKey());
                        }
                    }
            )*/
            .build(
                    new CacheLoader<String, Object>() {
                        @Override
                        public Object load(String s) throws Exception {
                            System.out.println("---------------s------------------");
                            return null;
                        }
                    }
            );

    /**
     * @Description: 获取缓存ID队列
     * @author: wp
     * @date: 2019/1/12 17:06
     */
    public static LinkedBlockingDeque<Long> getIdQueue() {
        @Nullable Object value = build.getIfPresent(ID_GENERATOR_KEY);

        if (value != null) {
            return (LinkedBlockingDeque<Long>) value;
        }
        return idQueue;
    }


    /**
     * @Description: 获取缓存
     * @author: wp
     * @date: 2019/1/12 15:20
     */
    public static Object get() {
        try {
            LinkedBlockingDeque<Long> idQueue = getIdQueue();
            if (idQueue != null) {
                return idQueue.poll();
            }
            return null;
        } catch (Exception e) {
            log.error("缓存不存在: ", e);
            return null;
        }
    }

    /**
     * @Description: 获取所有缓存
     * @author: wp
     * @date: 2019/1/12 15:30
     */
    public static ConcurrentMap<String, Object> getAllCache() {
        return build.asMap();
    }

    /**
     * @Description: 存入缓存
     * @author: wp
     * @date: 2019/1/12 15:22
     */
    public static void setCache(String key, Object value) {
        try {
            build.put(key, value);
        } catch (Exception e) {
            log.error("存入缓存失败");
        }
    }

    /**
     * @Description: 存入缓存
     * @author: wp
     * @date: 2019/1/12 15:22
     */
    public static void setCache(Map<String, Object> cacheMap) {
        try {
            build.putAll(cacheMap);
        } catch (Exception e) {
            log.error("存入缓存失败");
        }
    }

    /**
     * @Description: 删除缓存
     * @author: wp
     * @date: 2019/1/12 15:26
     */
    public static void del(String key) {
        try {
            build.invalidate(key);
        } catch (Exception e) {
            log.error("删除缓存失败");
        }
    }

    /**
     * @Description: 删除缓存
     * @author: wp
     * @date: 2019/1/12 15:26
     */
    public static void delAll() {
        try {
            build.invalidateAll();
        } catch (Exception e) {
            log.error("删除缓存失败");
        }
    }

    /**
     * @Description: 删除缓存
     * @author: wp
     * @date: 2019/1/12 15:26
     */
    public static void delAll(Iterable<Object> keys) {
        try {
            build.invalidateAll(keys);
        } catch (Exception e) {
            log.error("删除缓存失败");
        }
    }
}
