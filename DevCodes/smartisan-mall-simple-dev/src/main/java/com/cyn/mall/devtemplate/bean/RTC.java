package com.cyn.mall.devtemplate.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * 文件描述
 *
 * @ProjectName: mall-dev-template
 * @Package: com.cyn.mall.devtemplate.bean
 * @Date 2020/7/25 17:14
 * @Author:
 * @Version: 1.0
 * @Description: note
 **/
@Data
public class RTC implements Serializable {
    private static final long serialVersionUID = 1L;
    private int count;

    private String msg;
    private Object result;
    private String status;
}
