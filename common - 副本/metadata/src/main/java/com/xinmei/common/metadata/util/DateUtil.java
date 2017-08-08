package com.xinmei.common.metadata.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Toby on 2016/11/3.
 * 时间工具类
 */
public class DateUtil {

    private final static Logger logger= LoggerFactory.getLogger(DateUtil.class);
    public final static String TIME_FORMAT_YYYY_MM_DD_HH_MM_SS="yyyy-MM-dd HH:mm:ss";

    /**
     * 将时间字符串变为时间
     * @param time 时间字符串
     * @param format 时间格式
     * @return
     */
    public static Timestamp convertStrToTime(String time,String format){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat(format);
        Date date=null;
        try {
            date=simpleDateFormat.parse(time);
        } catch (ParseException e) {
            logger.error("format date error",e);
            return null;
        }
        if(null !=date) {
            return new Timestamp(date.getTime());
        }
        return null;
    }

    public static String getCurrentTime(String format){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat(format);
        return simpleDateFormat.format(new Date());
    }
}
