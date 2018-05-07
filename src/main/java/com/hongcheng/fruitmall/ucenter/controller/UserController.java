package com.hongcheng.fruitmall.ucenter.controller;

import com.hongcheng.fruitmall.common.constants.RestResponse;
import com.hongcheng.fruitmall.common.pojo.PageForm;
import com.hongcheng.fruitmall.common.pojo.PageList;
import com.hongcheng.fruitmall.common.pojo.SimpleUserInfo;
import com.hongcheng.fruitmall.ucenter.pojo.vo.CartVO;
import com.hongcheng.fruitmall.ucenter.pojo.vo.CollectVO;
import com.hongcheng.fruitmall.ucenter.pojo.vo.OrderVO;
import com.hongcheng.fruitmall.ucenter.pojo.vo.UserInfoVO;
import com.hongcheng.fruitmall.ucenter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/fruitmall/userCenter/v1")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/userInfo")
    public RestResponse<UserInfoVO> getUserInfo(@SessionAttribute(value = "userId") Integer userId) {
       return RestResponse.success(userService.getUserDetail(userId));
    }

    @GetMapping("/simpleUser")
    public RestResponse<SimpleUserInfo> getSimpleUser(@SessionAttribute(value = "userId",required = false) Integer userId) {
        return RestResponse.success(userService.getSimpleUser(userId));
    }

    @GetMapping("/orders")
    public RestResponse<PageList<OrderVO>> getOrders(@SessionAttribute("userId") Integer userId, PageForm form) {
        return RestResponse.success(userService.getOrders(userId,form));
    }

    @GetMapping("/carts")
    public RestResponse<PageList<CartVO>> getCarts(@SessionAttribute("userId") Integer userId) {
        return RestResponse.success(userService.getCart(userId));
    }

    @PostMapping("/cart")
    public RestResponse<Integer> addToCart(@SessionAttribute("userId")Integer userId,Integer productId) {
        return RestResponse.success(userService.addToCart(userId,productId));
    }

    @GetMapping("/cartNum")
    public RestResponse<Integer> getCartNum(@SessionAttribute(value = "userId",required = false) Integer userId) {
        return RestResponse.success(userService.getCartNum(userId));
    }

    @GetMapping("/collect")
    public RestResponse<PageList<CollectVO>> getCollect(@SessionAttribute("userId") Integer userId) {
        return RestResponse.success(userService.getCollectList(userId));
    }

    @PostMapping("/collect")
    public RestResponse<Integer> addToCollect(@SessionAttribute("userId")Integer userId,Integer productId) {
        return RestResponse.success(userService.addToCollect(userId,productId));
    }

    @PutMapping("/userInfo")
    public RestResponse<Integer> updateUserInfo(@SessionAttribute("userId") Integer userId,@RequestBody UserInfoVO vo) {
        return RestResponse.success(userService.updateUserInfo(userId,vo));
    }

    @DeleteMapping("/cart/{id}")
    public RestResponse<Integer> removeFromCart(@SessionAttribute("userId") Integer userId,@PathVariable Integer id) {
        return RestResponse.success(userService.removeFromCart(userId,id));
    }

    @DeleteMapping("/collect/{id}")
    public RestResponse<Integer> removeFromCollect(@SessionAttribute("userId") Integer userId,@PathVariable Integer id) {
        return RestResponse.success(userService.removeFromCollects(userId,id));
    }

    @PostMapping("/order")
    public RestResponse<Integer> createOrder(@SessionAttribute("userId") Integer userId, @RequestBody Map<Integer,Integer> formMap) {
        return RestResponse.success(userService.createOrder(userId,formMap));
    }

    @PatchMapping("/order/{id}/sign")
    public RestResponse<Integer> signOrder(@SessionAttribute("userId") Integer userId,@PathVariable Integer orderId) {
        return RestResponse.success(userService.signOrder(userId,orderId));
    }

}
