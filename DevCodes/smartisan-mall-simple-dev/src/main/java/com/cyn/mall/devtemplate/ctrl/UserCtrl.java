package com.cyn.mall.devtemplate.ctrl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cyn.common.exception.RRException;
import com.cyn.mall.devtemplate.entity.AddressEntity;
import com.cyn.mall.devtemplate.entity.UserEntity;
import com.cyn.mall.devtemplate.service.AddressService;
import com.cyn.mall.devtemplate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;


/**
 * @author chenyanan
 * Created by chenyanan on 2020/7/27
 */
@Service
public class UserCtrl {
    @Autowired
    private UserService userService;

    @Autowired
    AddressService addressService;

    /**
     * 从cookies获取用户id
     *
     * @param httpServletRequest
     * @return
     */
    public Long getUserIdforReqCookies(HttpServletRequest httpServletRequest) {
        Cookie[] cookies = httpServletRequest.getCookies();
        for (Cookie cookie : cookies) {
            if ("userId".equals(cookie.getName())) {
                Integer inputUserId = Integer.parseInt(cookie.getValue());
                // 查询当前用户是否存在
                UserEntity byId = userService.getById(inputUserId);
                if (byId != null) {
                    return byId.getUserId();
                } else {
                    throw new RRException("用户不存在");
                }
            }
        }
        throw new RRException("未登录");
    }

    /**
     * 更新收货地址为默认收货地址
     * 其余自动变为非默认
     *
     * @param productId
     * @param userId
     * @return
     */
    public boolean putAddressDefault(Integer productId, Integer userId) {
        QueryWrapper<AddressEntity> queryWrapperOff = new QueryWrapper<>();
        queryWrapperOff.eq("user_id", userId);
        AddressEntity addressEntityOff = new AddressEntity();
        addressEntityOff.setIsDefault(0);
        // 先全部变成"0"
        boolean updateOff = addressService.update(queryWrapperOff);
        QueryWrapper<AddressEntity> queryWrapperOn = new QueryWrapper<>();

        queryWrapperOn.eq("user_id", userId).eq("product_id", productId);
        AddressEntity addressEntityOn = new AddressEntity();
        addressEntityOn.setIsDefault(1);
        // 指定地址设为默认
        boolean updateOn = addressService.update(addressEntityOn,queryWrapperOff);
        return updateOff && updateOn;
    }

    /**
     * 更新某个用户收货地址全为非默认收货地址
     *
     * @param userId
     * @return
     */
    public boolean putAddressDefault(Integer userId) {
        QueryWrapper<AddressEntity> queryWrapperOff = new QueryWrapper<>();
        queryWrapperOff.eq("user_id", userId);
        AddressEntity addressEntityOff = new AddressEntity();
        addressEntityOff.setIsDefault(0);
        // 先全部变成"0"
        boolean updateOff = addressService.update(addressEntityOff,queryWrapperOff);
        QueryWrapper<AddressEntity> queryWrapperOn = new QueryWrapper<>();
        return updateOff;
    }
}
