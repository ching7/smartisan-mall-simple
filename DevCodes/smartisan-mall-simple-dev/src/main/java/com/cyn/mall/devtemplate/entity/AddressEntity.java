package com.cyn.mall.devtemplate.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * @author chenyn
 * @email 775608598@qq.com
 * @date 2020-07-25 14:59:41
 */
@Data
@TableName("sys_address")
public class AddressEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 收货地址编号
     */
    @TableId
    private Long addressId;
    /**
     * 用户编号
     */
    private String userId;
    /**
     * 登陆账号
     */
    private String userName;
    /**
     * 详细地址
     */
    private String streetName;
    /**
     * 电话号码
     */
    private String tel;
    /**
     * 是否为默认
     */
    private Integer isDefault;

}
