package com.cyn.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 文件描述
 *
 * @ProjectName: mall-dev-template
 * @Package: com.cyn.common.utils
 * @Date 2020/8/1 14:42
 * @Author:
 * @Version: 1.0
 * @Description: note
 **/
public class SerialUtils {

    public static String getSerialNo() {
        SimpleDateFormat hHmmss = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        Date now = new Date();
        return hHmmss.format(now);
    }
}
