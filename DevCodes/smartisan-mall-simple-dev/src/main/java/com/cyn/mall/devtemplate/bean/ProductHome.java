package com.cyn.mall.devtemplate.bean;

import com.cyn.mall.devtemplate.entity.ProductEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 文件描述
 *
 * @ProjectName: mall-dev-template
 * @Package: com.cyn.mall.devtemplate.bean
 * @Date 2020/7/25 16:38
 * @Author:
 * @Version: 1.0
 * @Description: note
 **/
@Data
public class ProductHome implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 商品列表
     */
    private List<HomeFloor> homeFloors;

    /**
     * 热门商品
     */
    private List<ProductEntity> homeHot;
}
