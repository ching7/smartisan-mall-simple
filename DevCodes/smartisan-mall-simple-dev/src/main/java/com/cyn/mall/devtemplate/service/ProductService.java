package com.cyn.mall.devtemplate.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cyn.common.utils.PageUtils;
import com.cyn.mall.devtemplate.entity.ProductEntity;

import java.util.Map;

/**
 * @author chenyn
 * @email 775608598@qq.com
 * @date 2020-07-25 14:59:41
 */
public interface ProductService extends IService<ProductEntity> {

    PageUtils queryPage(Map<String, Object> queryPageParams, QueryWrapper<ProductEntity> queryWrapper);

    PageUtils queryPage(Map<String, Object> queryPageParams);
}

