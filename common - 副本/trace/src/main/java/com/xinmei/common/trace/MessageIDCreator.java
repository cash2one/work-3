package com.xinmei.common.trace;

import org.unidal.helper.Splitters;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName: com.xinmei.common.trace.MessageIDCreator
 * @Description: 消息id生成器
 * @Author xbzhu
 * @Date 2017年03月02日 14:03
 */
public class MessageIDCreator {

    private static final long HOUR = 3600 * 1000L;
    private static AtomicInteger index=new AtomicInteger();

    private static String ip="172.16.2.199";

    private static String appName="etrustApp";


    public static String getMessageId(){
        StringBuilder builder=new StringBuilder(appName.length()+120);
        builder.append(appName).append("-");
        List<String> items = Splitters.by(".").noEmptyItem().split(ip);
        byte[] bytes = new byte[4];
        for (int i = 0; i < 4; i++) {
            bytes[i] = (byte) Integer.parseInt(items.get(i));
//            System.out.println(Integer.toHexString(bytes[i]));
        }
        StringBuilder sb = new StringBuilder(bytes.length / 2);
        for (byte b : bytes) {
            sb.append(Integer.toHexString((b >> 4) & 0x0F));
//            System.out.println(Integer.toHexString((b >> 4) & 0x0F));
            sb.append(Integer.toHexString(b & 0x0F));
//            System.out.println(Integer.toHexString(b & 0x0F));
        }
        builder.append(sb.toString()).append("-");
        long timestamp = System.currentTimeMillis();

        builder.append(timestamp / HOUR).append("-").append(index.incrementAndGet());
        return builder.toString();
    }


    public static void main(String[] args){
        System.out.println(getMessageId());
    }
}
