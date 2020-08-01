package com.cyn.mall.devtemplate.controller;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.*;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.cyn.common.utils.DateUtils;
import com.cyn.common.utils.SerialUtils;
import com.cyn.mall.devtemplate.bean.RT;
import com.cyn.mall.devtemplate.bean.RTC;
import com.cyn.mall.devtemplate.ctrl.UserCtrl;
import com.cyn.mall.devtemplate.entity.AddressEntity;
import com.cyn.mall.devtemplate.entity.CartEntity;
import com.cyn.mall.devtemplate.entity.OrderEntity;
import com.cyn.mall.devtemplate.service.AddressService;
import com.cyn.mall.devtemplate.service.CartService;
import com.cyn.mall.devtemplate.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.cyn.mall.devtemplate.entity.UserEntity;
import com.cyn.mall.devtemplate.service.UserService;
import com.cyn.common.utils.PageUtils;
import com.cyn.common.utils.R;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @author chenyn
 * @email 775608598@qq.com
 * @date 2020-07-25 14:59:41
 */
@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private CartService cartService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private UserCtrl userCtrl;

    @Autowired
    private OrderService orderService;


    /**
     * 购物车产品全选
     *
     * @return
     */
    @RequestMapping(value = "/editCheckAll", method = RequestMethod.POST, name = "购物车产品全选")
    public RT putEditCheckAll(@RequestBody Map<String, Object> params, HttpServletRequest httpRequest) {
        RT rt = new RT();
        boolean checkAll = (boolean) params.get("checkAll");
        if (checkAll) {
            Long userIdforReqCookies = userCtrl.getUserIdforReqCookies(httpRequest);
            QueryWrapper<CartEntity> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("user_id", userIdforReqCookies);
            CartEntity cartEntity = new CartEntity();
            cartEntity.setChecked("1");
            boolean update = cartService.update(cartEntity, queryWrapper);
            rt.setResult("suc");
            rt.setStatus("0");
            rt.setMsg("修改成功");
        } else {
            rt.setResult("err");
            rt.setStatus("1");
            rt.setMsg("修改失败");
        }
        return rt;
    }

    /**
     * 修改购物车产品数量
     *
     * @return
     */
    @RequestMapping(value = "/cartEdit", method = RequestMethod.POST, name = "修改购物车产品数量")
    public RT putCartEdit(@RequestBody Map<String, Object> params, HttpServletRequest httpRequest) {
        RT rt = new RT();
        Integer productId = (Integer) params.get("productId");
        Integer productNum = (Integer) params.get("productNum");
        String checked = (String) params.get("checked");

        Long userIdforReqCookies = userCtrl.getUserIdforReqCookies(httpRequest);

        QueryWrapper<CartEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("product_id", productId)
                .eq("user_id", userIdforReqCookies);
        CartEntity cartEntity = new CartEntity();
        cartEntity.setProductNum(productNum);
        cartEntity.setChecked(checked);
        boolean update = cartService.update(cartEntity, queryWrapper);
        if (update) {
            rt.setResult("suc");
            rt.setStatus("0");
            rt.setMsg("修改成功");
        } else {
            rt.setResult("err");
            rt.setStatus("1");
            rt.setMsg("修改数量失败");
        }
        return rt;
    }

    /**
     * 删除购物车
     *
     * @return
     */
    @RequestMapping(value = "/cartDel", method = RequestMethod.POST, name = "删除购物车")
    public RT deleteCartDel(@RequestBody Map<String, Object> params, HttpServletRequest httpRequest) {
        RT rt = new RT();
        Integer productId = (Integer) params.get("productId");
        Long userIdforReqCookies = userCtrl.getUserIdforReqCookies(httpRequest);

        QueryWrapper<CartEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("product_id", productId).eq("user_id", userIdforReqCookies);
        boolean remove = cartService.remove(queryWrapper);
        if (remove) {
            rt.setResult("suc");
            rt.setStatus("0");
            rt.setMsg("删除成功");
        } else {
            rt.setResult("err");
            rt.setStatus("1");
            rt.setMsg("删除购物车失败");
        }
        return rt;
    }

    /**
     * 用户注册
     *
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST, name = "用户注册")
    public RT postRegister(@RequestBody Map<String, Object> params) {
        RT rt = new RT();
        String inputUserName = (String) params.get("userName");
        String inputUserPwd = (String) params.get("userPwd");
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", inputUserName);
        List<UserEntity> list = userService.list(queryWrapper);
        if (list.size() > 0) {
            rt.setResult("err");
            rt.setStatus("1");
            rt.setMsg("账号已存在");
        } else {
            UserEntity userEntityAdd = new UserEntity();
            userEntityAdd.setAvatar("https://gitee.com/ching7777/gitee_graph_bed/raw/master/img/20200729185413.png");
            userEntityAdd.setName("游客" + DateUtils.getCurrDate() + DateUtils.getCurrTime());
            userEntityAdd.setUserName(inputUserName);
            userEntityAdd.setUserPwd(inputUserPwd);
            boolean save = userService.save(userEntityAdd);
            if (save) {
                rt.setResult("suc");
                rt.setStatus("0");
                rt.setMsg("注册成功");
            }
        }
        return rt;
    }

    /**
     * 删除订单
     *
     * @return
     */
    @RequestMapping(value = "/delOrder", method = RequestMethod.POST, name = "删除订单")
    public RT delOrder(@RequestBody Map<String, Object> params, HttpServletRequest httpRequest) {
        RT rt = new RT();
        Long userIdforReqCookies = userCtrl.getUserIdforReqCookies(httpRequest);
        Long inputOrderId = Long.parseLong((String) params.get("orderId"));
        QueryWrapper<OrderEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userIdforReqCookies).eq("order_id", inputOrderId);
        boolean remove = orderService.remove(queryWrapper);
        if (remove) {
            rt.setResult("suc");
            rt.setStatus("0");
            rt.setMsg("");
        } else {
            rt.setResult("err");
            rt.setStatus("1");
            rt.setMsg("");
        }
        return rt;
    }

    /**
     * 订单列表
     *
     * @return
     */
    @RequestMapping(value = "/orderList", method = RequestMethod.POST, name = "订单列表")
    public RT postPayMent(HttpServletRequest httpRequest) {
        RT rt = new RT();
        Long userIdforReqCookies = userCtrl.getUserIdforReqCookies(httpRequest);
        QueryWrapper<OrderEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userIdforReqCookies).orderByDesc("create_date");
        List<OrderEntity> orderEntityList = orderService.list(queryWrapper);
        rt.setResult(orderEntityList);
        rt.setStatus("0");
        rt.setMsg("");
        return rt;
    }

    /**
     * 生成订单
     *
     * @return
     */
    @RequestMapping(value = "/payMent", method = RequestMethod.POST, name = "支付")
    public RT postPayMent(@RequestBody Map<String, Object> params, HttpServletRequest httpRequest) {
        RT rt = new RT();
        long inputAddressId = Long.parseLong((String) params.get("addressId"));
        long inputOrderTotal = Long.parseLong((String) params.get("orderTotal"));
        //addressId, orderTotal, productId, productNum
        Long userIdforReqCookies = userCtrl.getUserIdforReqCookies(httpRequest);
        QueryWrapper<AddressEntity> addressEntityQueryWrapper = new QueryWrapper<>();
        addressEntityQueryWrapper.eq("user_id", userIdforReqCookies);
        // 当前用户的收货地址
        List<AddressEntity> addressEntityList = addressService.list(addressEntityQueryWrapper);
        QueryWrapper<CartEntity> cartEntityQueryWrapper = new QueryWrapper<>();
        cartEntityQueryWrapper.eq("user_id", userIdforReqCookies);
        cartEntityQueryWrapper.eq("checked", "1");
        // 当前用户的购物车列表 / 且已勾选的checked=1
        List<CartEntity> cartEntityList = cartService.list(cartEntityQueryWrapper);

        //生成订单
        addressEntityList.forEach(addressEntity -> {
            if (addressEntity.getAddressId().equals(inputAddressId)) {
                OrderEntity orderEntity = new OrderEntity();
                orderEntity.setUserId(userIdforReqCookies.intValue());
                orderEntity.setAddressId(Math.toIntExact(inputAddressId));
                orderEntity.setGoodsList(JSONObject.toJSONString(cartEntityList));
                orderEntity.setOrderTotal(BigDecimal.valueOf(inputOrderTotal));
                orderEntity.setCreateDate(DateUtils.getCurrDate() + DateUtils.getCurrTime());
                orderEntity.setOrderStatus("1"); //1 已支付
                boolean save = orderService.save(orderEntity);
                if (save) {
                    // 支付成功删除购物商品
                    // 已勾选的checked=1
                    cartService.remove(cartEntityQueryWrapper);
                    rt.setMsg("suc");
                    rt.setStatus("0");
                    rt.setResult("");
                } else {
                    rt.setMsg("err");
                    rt.setStatus("1");
                    rt.setResult("");
                }
            }
        });
        return rt;
    }

    /**
     * 删除收获地址
     *
     * @param httpServletRequest
     * @return
     */
    @RequestMapping(value = "/addressDel", method = RequestMethod.POST, name = "删除收获地址删除收获地址")
    public RT delAddressList(HttpServletRequest httpServletRequest, @RequestBody Map<String, Object> params) {
        RT rt = new RT();
        Integer inputAddressId = (Integer) params.get("addressId");
        Long userIdforReqCookies = userCtrl.getUserIdforReqCookies(httpServletRequest);
        if (userIdforReqCookies != null && inputAddressId > 0) {
            boolean del = addressService.removeById(inputAddressId);
            if (del) {
                rt.setMsg("删除成功");
                rt.setStatus("0");
                rt.setResult("");
            } else {
                rt.setMsg("删除失败");
                rt.setStatus("1");
                rt.setResult("");
            }
        } else {
            rt.setMsg("未登录");
            rt.setStatus("1");
            rt.setResult("");
        }
        return rt;
    }

    /**
     * 新增收获地址
     *
     * @param httpServletRequest
     * @return
     */
    @RequestMapping(value = "/addressAdd", method = RequestMethod.POST, name = "修改收获地址")
    public RT postAddressList(HttpServletRequest httpServletRequest, @RequestBody Map<String, Object> params) {
        RT rt = new RT();
        String inputUserName = (String) params.get("userName");
        String inputTel = (String) params.get("tel");
        String inputStreetName = (String) params.get("streetName");
        Integer inputIsDefault = (Integer) params.get("isDefault");
        Long userIdforReqCookies = userCtrl.getUserIdforReqCookies(httpServletRequest);
        if (inputIsDefault == 1) {
            userCtrl.putAddressDefault(userIdforReqCookies.intValue());
        }
        if (userIdforReqCookies != null && !inputUserName.trim().isEmpty() && !inputStreetName.trim().isEmpty()) {
            AddressEntity addressEntity = new AddressEntity();
            addressEntity.setUserId(Long.toString(userIdforReqCookies));
            addressEntity.setUserName(inputUserName);
            addressEntity.setTel(inputTel);
            addressEntity.setStreetName(inputStreetName);
            addressEntity.setIsDefault(inputIsDefault);
            boolean add = addressService.save(addressEntity);
            if (add) {
                rt.setMsg("suc");
                rt.setStatus("0");
            } else {
                rt.setMsg("新增失败");
                rt.setStatus("1");
            }
        } else {
            rt.setMsg("未登录");
            rt.setStatus("1");
        }
        rt.setResult("");
        return rt;
    }

    /**
     * 修改收获地址
     *
     * @param httpServletRequest
     * @return
     */
    @RequestMapping(value = "/addressUpdate", method = RequestMethod.POST, name = "修改收获地址")
    public RT putAddressList(HttpServletRequest httpServletRequest, @RequestBody Map<String, Object> params) {
        RT rt = new RT();
        Integer inputAddressId = (Integer) params.get("addressId");
        String inputUserName = (String) params.get("userName");
        String inputTel = (String) params.get("tel");
        String inputStreetName = (String) params.get("streetName");
        Integer inputIsDefault = (Integer) params.get("isDefault");
        Long userIdforReqCookies = userCtrl.getUserIdforReqCookies(httpServletRequest);
        if (inputIsDefault == 1) {
            userCtrl.putAddressDefault(userIdforReqCookies.intValue());
        }
        if (userIdforReqCookies != null && inputAddressId > 0 && !inputUserName.trim().isEmpty() && !inputStreetName.trim().isEmpty()) {
            UpdateWrapper<AddressEntity> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("user_id", userIdforReqCookies).eq("address_id", inputAddressId);
            AddressEntity addressEntity = new AddressEntity();
            addressEntity.setStreetName(inputStreetName);
            addressEntity.setUserName(inputUserName);
            addressEntity.setIsDefault(inputIsDefault);

            addressEntity.setTel(inputTel);
            boolean update = addressService.update(addressEntity, updateWrapper);
            if (update) {
                rt.setMsg("suc");
                rt.setStatus("0");
                rt.setResult("");
            } else {
                rt.setMsg("更新失败");
                rt.setStatus("1");
                rt.setResult("");
            }
        } else {
            rt.setMsg("未登录");
            rt.setStatus("1");
        }
        return rt;
    }

    /**
     * 查询单个收获地址
     *
     * @param httpServletRequest
     * @return
     */
    @RequestMapping(value = "/addressListById", method = RequestMethod.POST, name = "查询单个收获地址")
    public RT getAddressListByID(HttpServletRequest httpServletRequest, @RequestBody Map<String, Object> params) {
        RT rt = new RT();
        Long userIdforReqCookies = userCtrl.getUserIdforReqCookies(httpServletRequest);
        Integer inputAddressId = Integer.parseInt((String) params.get("addressId"));
        QueryWrapper<AddressEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userIdforReqCookies).eq("address_id", inputAddressId);
        List<AddressEntity> addressEntityList = addressService.list(queryWrapper);
        if (addressEntityList.size() > 0) {
            rt.setMsg("suc");
            rt.setStatus("0");
            rt.setResult(addressEntityList);
        } else {
            rt.setMsg("err");
            rt.setStatus("0");
            rt.setResult(" ");
        }
        return rt;
    }

    /**
     * 查询所有收获地址
     *
     * @param httpServletRequest
     * @return
     */
    @RequestMapping(value = "/addressList", method = RequestMethod.POST, name = "查询收获地址")
    public RT getAddressList(HttpServletRequest httpServletRequest) {
        RT rt = new RT();
        Long userIdforReqCookies = userCtrl.getUserIdforReqCookies(httpServletRequest);
        if (userIdforReqCookies != null) {
            QueryWrapper<AddressEntity> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("user_id", userIdforReqCookies);
            List<AddressEntity> addressEntityList = addressService.list(queryWrapper);
            if (addressEntityList.size() > 0) {
                rt.setMsg("suc");
                rt.setStatus("0");
                rt.setResult(addressEntityList);
            } else {
                rt.setMsg("suc");
                rt.setStatus("0");
                rt.setResult(addressEntityList);
            }
        } else {
            rt.setMsg("未登录");
            rt.setStatus("1");
        }
        return rt;
    }

    /**
     * 查询购物车
     *
     * @return
     */
    @RequestMapping(value = "/cartList", method = RequestMethod.POST, name = "查询购物车")
    public RTC getCartList(HttpServletRequest httpServletRequest) {
        RTC rtc = new RTC();
        Long userIdforReqCookies = userCtrl.getUserIdforReqCookies(httpServletRequest);
        if (userIdforReqCookies != null) {
            QueryWrapper<CartEntity> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("user_id", userIdforReqCookies);
            List<CartEntity> listCarts = cartService.list(queryWrapper);
            rtc.setCount(listCarts.size());
            rtc.setMsg("suc");
            rtc.setResult(listCarts);
            rtc.setStatus("1");
        } else {
            rtc.setMsg("未登录");
            rtc.setStatus("1");
        }

        return rtc;
    }

    /**
     * 获取用户信息
     *
     * @param httpServletRequest
     * @return
     */
    @RequestMapping(value = "/userInfo", method = RequestMethod.POST)
    public RT getUserInfo(HttpServletRequest httpServletRequest) {
        RT rt = new RT();
        try {
            Long userIdforReqCookies = userCtrl.getUserIdforReqCookies(httpServletRequest);
            UserEntity userEntity = userService.getById(userIdforReqCookies);
            if (userEntity != null) {
                rt.setMsg("suc");
                rt.setResult(userEntity);
                // todo 前端判断是否登陆存在问题
                rt.setStatus("0");
            } else {
                rt.setMsg("未登录");
                rt.setStatus("1");
            }
        } catch (Exception e) {
            rt.setMsg("未登录");
            rt.setStatus("1");
        }
        return rt;
    }

    /**
     * 登陆
     *
     * @param params
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public RT login(@RequestBody Map<String, Object> params, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        RT rt = new RT();
        String userName = (String) params.get("userName");
        String userPwd = (String) params.get("userPwd");
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("user_name", userName)
                .eq("user_pwd", userPwd);
        UserEntity userEntity = userService.getOne(queryWrapper);
        if (userEntity != null) {
            Cookie cookie = new Cookie("userId", userEntity.getUserId().toString());
            cookie.setPath("/");
            cookie.setMaxAge(7200);
            httpServletResponse.addCookie(cookie);
            rt.setMsg("登陆成功");
            rt.setResult(userEntity);
            rt.setStatus("0");
        } else {
            rt.setMsg("账号或者密码错误");
            rt.setStatus("1");
        }
        return rt;
    }

    /**
     * 登出
     *
     * @param params
     * @return
     */
    @RequestMapping(value = "/loginOut", method = RequestMethod.POST)
    public RT loginOut(@RequestBody Map<String, Object> params, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        RT rt = new RT();
        Long userIdforReqCookies = userCtrl.getUserIdforReqCookies(httpServletRequest);
        if (userIdforReqCookies != null) {
            Cookie cookie = new Cookie("userId", null);
            cookie.setPath("/");
            cookie.setMaxAge(0);
            httpServletResponse.addCookie(cookie);
            rt.setMsg("登出成功");
            rt.setResult("");
            rt.setStatus("0");
        } else {
            rt.setMsg("未登录");
            rt.setStatus("1");
        }
        return rt;
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("devtemplate:user:list")
    public R list(@RequestBody Map<String, Object> params) {
        PageUtils page = userService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{userId}")
    //@RequiresPermissions("devtemplate:user:info")
    public R info(@PathVariable("userId") Long userId) {
        UserEntity user = userService.getById(userId);

        return R.ok().put("user", user);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("devtemplate:user:save")
    public R save(@RequestBody UserEntity user) {
        userService.save(user);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("devtemplate:user:update")
    public R update(@RequestBody UserEntity user) {
        userService.updateById(user);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("devtemplate:user:delete")
    public R delete(@RequestBody Long[] userIds) {
        userService.removeByIds(Arrays.asList(userIds));

        return R.ok();
    }

}
