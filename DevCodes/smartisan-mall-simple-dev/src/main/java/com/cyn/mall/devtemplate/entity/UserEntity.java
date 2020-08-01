package com.cyn.mall.devtemplate.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * 
 * @author chenyn
 * @email 775608598@qq.com
 * @date 2020-07-25 14:59:41
 */
@Data
@TableName("sys_user")
public class UserEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long userId;
	/**
	 * 用户名
	 */
	private String name;
	/**
	 * 头像
	 */
	private String avatar;
	/**
	 * 登陆账号
	 */
	private String userName;
	/**
	 * 登陆密码
	 */
	private String userPwd;
	/**
	 * 购物车
	 */
	private String cartId;
	/**
	 * 收获地址
	 */
	private String addressId;

}
