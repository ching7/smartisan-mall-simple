package com.cyn.mall.devtemplate.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
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
@TableName("sys_order")
public class OrderEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 订单编号
	 */
	@TableId
	private Long orderId;
	/**
	 * 用户编号
	 */
	private Integer userId;
	/**
	 * 订单商品
	 */
	private String goodsList;
	/**
	 * 订单总价
	 */
	private BigDecimal orderTotal;
	/**
	 * 收货地址
	 */
	private Integer addressId;
	/**
	 * 订单状态
	 */
	private String orderStatus;
	/**
	 * 创建时间
	 */
	private String createDate;

}
