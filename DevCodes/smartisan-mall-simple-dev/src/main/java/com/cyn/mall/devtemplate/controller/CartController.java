package com.cyn.mall.devtemplate.controller;

import java.util.Arrays;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.cyn.mall.devtemplate.entity.CartEntity;
import com.cyn.mall.devtemplate.service.CartService;
import com.cyn.common.utils.PageUtils;
import com.cyn.common.utils.R;


/**
 * @author chenyn
 * @email 775608598@qq.com
 * @date 2020-07-25 14:59:41
 */
@RestController
@RequestMapping("/api/cart")
public class CartController {
    @Autowired
    private CartService cartService;


    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("devtemplate:cart:list")
    public R list(@RequestBody Map<String, Object> params) {
        PageUtils page = cartService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{cartId}")
    //@RequiresPermissions("devtemplate:cart:info")
    public R info(@PathVariable("cartId") Long cartId) {
        CartEntity cart = cartService.getById(cartId);

        return R.ok().put("cart", cart);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("devtemplate:cart:save")
    public R save(@RequestBody CartEntity cart) {
        cartService.save(cart);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("devtemplate:cart:update")
    public R update(@RequestBody CartEntity cart) {
        cartService.updateById(cart);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("devtemplate:cart:delete")
    public R delete(@RequestBody Long[] cartIds) {
        cartService.removeByIds(Arrays.asList(cartIds));

        return R.ok();
    }

}
