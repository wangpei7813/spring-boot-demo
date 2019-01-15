package com.wang.guava;


import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.util.StopWatch;

import java.net.NetworkInterface;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author wp
 * @date 2019/1/12 13:53
 */
public class GuavaTest {

    /** 开始时间截 (2015-01-01) */
    private static final long twepoch = 1420041600000L;

    /** 机器id所占的位数 */
    private static final long workerIdBits = 5L;

    /** 数据标识id所占的位数 */
    private static final long datacenterIdBits = 5L;

    /** 支持的最大机器id，结果是31 (这个移位算法可以很快的计算出几位二进制数所能表示的最大十进制数) */
    private static final long maxWorkerId = -1L ^ (-1L << workerIdBits);

    /** 支持的最大数据标识id，结果是31 */
    private static final long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);

    /** 序列在id中占的位数 */
    private static final long sequenceBits = 12L;

    /** 机器ID向左移12位 */
    private static final long workerIdShift = sequenceBits;

    /** 数据标识id向左移17位(12+5) */
    private static final long datacenterIdShift = sequenceBits + workerIdBits;

    /** 时间截向左移22位(5+5+12) */
    private static final long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;

    /** 生成序列的掩码，这里为4095 (0b111111111111=0xfff=4095) */
    private static final long sequenceMask = -1L ^ (-1L << sequenceBits);

    public static void main(String[] args) throws Exception {

        AtomicLong atomicLong = new AtomicLong();


        /*6490410575342862337
        6490410575342862338
        6490410575342862339
        */

        StopWatch sw = new StopWatch();
        sw.start("开始");
        int i = "".hashCode();
        System.out.println(i);
        System.out.println((System.currentTimeMillis() << timestampLeftShift)
                | ("order-service".hashCode() << datacenterIdShift)
                | (i  << 11)
                | atomicLong.addAndGet(1));
        System.out.println((System.currentTimeMillis() << timestampLeftShift)
                | ("order-service".hashCode() << datacenterIdShift)
                | (i  << 11)
                | atomicLong.addAndGet(1));
        System.out.println((System.currentTimeMillis() << timestampLeftShift)
                | ("order-service".hashCode() << datacenterIdShift)
                | (1743763679  << 11)
                | atomicLong.addAndGet(1));

        /*for (int i = 0; i < 100000; i++) {
            System.out.println((System.currentTimeMillis() << timestampLeftShift)
                    | atomicLong.addAndGet(1));

        }*/

        sw.stop();

        System.out.println(sw.prettyPrint());

        /*DecimalFormat decimalFormat = new DecimalFormat("0000");

        AtomicLong atomicLong = new AtomicLong(12345);

        System.out.println(decimalFormat.format(atomicLong.addAndGet(1)));


        System.out.println(System.currentTimeMillis());

        LinkedBlockingDeque<Long> idQueue = IdGeneratorUtil.idQueue;

        StringBuilder sb = new StringBuilder();
        sb.append(x);



        IdGeneratorUtil.setCache("idKey", idQueue);*/


        /*IdGeneratorUtil.getAllCache().forEach((String k, Object v) -> {
            System.out.println(k + "-------" + v);
            LinkedBlockingDeque<Long> idList = (LinkedBlockingDeque<Long>) v;

            System.out.println("size ======== " + idList.size());

            idList.forEach(System.out::println);

            System.out.println("--------------------------获取" + idList.poll());

            System.out.println("size ======== " + idList.size());

            idList.forEach(System.out::println);
        });*/

        /*DecimalFormat decimalFormat = new DecimalFormat("00000000");

        AtomicLong atomicLong = new AtomicLong();

        System.out.println(decimalFormat.format(atomicLong.addAndGet(1)));
        System.out.println(decimalFormat.format(atomicLong.addAndGet(1)));

        // 1276384474
        System.out.println(getMacAddress().hashCode());
        // 562889526
        System.out.println("order-service".hashCode());*/

    }

    private static Long getId() {
        DecimalFormat decimalFormat = new DecimalFormat("00000");
        return null;
    }

    public static String getMacAddress() {
        try {
            Enumeration<NetworkInterface> allNetInterfaces = NetworkInterface.getNetworkInterfaces();
            byte[] mac = null;
            while (allNetInterfaces.hasMoreElements()) {
                NetworkInterface netInterface = allNetInterfaces.nextElement();
                if (!netInterface.isLoopback() && !netInterface.isVirtual() && !netInterface.isPointToPoint() && netInterface.isUp()) {
                    mac = netInterface.getHardwareAddress();
                    if (mac != null) {
                        StringBuilder sb = new StringBuilder();
                        for (int i = 0; i < mac.length; i++) {
                            sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
                        }
                        if (sb.length() > 0) {
                            return sb.toString();
                        }
                    }
                }
            }
        } catch (Exception e) {
//                        _logger.error("MAC地址获取失败", e);
        }
        return "";
    }

}
