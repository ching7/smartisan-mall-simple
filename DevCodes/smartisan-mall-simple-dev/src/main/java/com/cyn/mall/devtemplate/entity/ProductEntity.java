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
@TableName("sys_product")
public class ProductEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 产品id
	 */
	@TableId
	private Long productId;
	/**
	 * 零售价
	 */
	private BigDecimal salePrice;
	/**
	 * 产品名称
	 */
	private String productName;
	/**
	 * 产品小图
	 */
	private String productImageSmall;
	/**
	 * 产品大图

	 */
	private String productImageBig;
	/**
	 * 产品详细信息
	 */
	private String productMsg;
	/**
	 * 库存
	 */
	private Integer stock;
	/**
	 * 标题
	 */
	private String subTitle;
	/**
	 * 限制购买数量
	 */
	private Integer limitNum;

}
