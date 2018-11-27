package com.wang.service.impl;

import com.wang.dao.UserInfoDao;
import com.wang.model.UserInfo;
import com.wang.service.UserInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.*;

@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Resource
    private UserInfoDao userInfoDao;

    @Override
    public UserInfo findByUsername(String username) {
        System.out.println("UserInfoServiceImpl.findByUsername()");
        return userInfoDao.findByUsername(username);
    }


    public void test(List<UserInfo> list) throws Exception {

        int count = 1000;
        int listSize = list.size();
        int runSize = (listSize / count) + 1;

//        ThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(5);
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(5);
        CountDownLatch begin = new CountDownLatch(1);
        CountDownLatch end = new CountDownLatch(runSize);

        List<UserInfo> newList;
        for (int i = 0; i < runSize; i++) {

            if ((i + 1) == runSize) {
                int startIndex = (i * count);
                int endIndex = list.size();
                newList = list.subList(startIndex, endIndex);
            } else {
                int startIndex = i * count;
                int endIndex = (i + 1) * count;
                newList = list.subList(startIndex, endIndex);
            }

            ExecupteHp hpRunnable = new ExecupteHp(newList, end, begin);
            executor.execute(hpRunnable);

        }
        begin.countDown();
        end.await();
        executor.shutdown();

    }


    class ExecupteHp implements Runnable {
        private List<UserInfo> list;
        private CountDownLatch begin;
        private CountDownLatch end;

        ExecupteHp(List<UserInfo> list, CountDownLatch end, CountDownLatch begin) {
            this.list = list;
            this.begin = begin;
            this.end = end;
        }

        @Override
        public void run() {

            try {
                userInfoDao.batchInsert(list);
                begin.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                end.countDown();
            }

        }
    }
}