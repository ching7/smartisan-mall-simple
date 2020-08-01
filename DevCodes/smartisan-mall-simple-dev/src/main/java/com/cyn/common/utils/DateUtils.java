package com.cyn.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 文件描述
 *
 * @ProjectName: mall-dev-template
 * @Package: com.cyn.common.utils
 * @Date 2020/7/29 18:43
 * @Author:
 * @Version: 1.0
 * @Description: note
 **/
public class DateUtils {

    public static String getCurrDate() {
        SimpleDateFormat yyyyMMdd = new SimpleDateFormat("yyyyMMdd");
        Date now = new Date();

        return yyyyMMdd.format(now);
    }

    public static String getCurrTime() {
        SimpleDateFormat hHmmss = new SimpleDateFormat("HHmmss");
        Date now = new Date();
        return hHmmss.format(now);
    }
}
