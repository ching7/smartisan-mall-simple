package com.cyn.mall.devtemplate.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cyn.common.utils.PageUtils;
import com.cyn.common.utils.Query;

import com.cyn.mall.devtemplate.dao.CartDao;
import com.cyn.mall.devtemplate.entity.CartEntity;
import com.cyn.mall.devtemplate.service.CartService;


@Service("cartService")
public class CartServiceImpl extends ServiceImpl<CartDao, CartEntity> implements CartService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CartEntity> page = this.page(
                new Query<CartEntity>().getPage(params),
                new QueryWrapper<CartEntity>()
        );

        return new PageUtils(page);
    }

}