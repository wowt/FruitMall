package com.hongcheng.fruitmall.ucenter.service;

import com.hongcheng.fruitmall.common.pojo.PageForm;
import com.hongcheng.fruitmall.common.pojo.PageList;
import com.hongcheng.fruitmall.common.pojo.SimpleUserInfo;
import com.hongcheng.fruitmall.ucenter.pojo.vo.CartVO;
import com.hongcheng.fruitmall.ucenter.pojo.vo.CollectVO;
import com.hongcheng.fruitmall.ucenter.pojo.vo.OrderVO;
import com.hongcheng.fruitmall.ucenter.pojo.vo.UserInfoVO;

import java.util.Map;

public interface UserService {

    /**
     * 获取用户信息
     * @return
     */
    UserInfoVO getUserDetail(Integer userId);

    /**
     * 修改信息
     * @return
     */
    Integer updateUserInfo(Integer userId,UserInfoVO infoVO);

    /**
     * 查看订单列表
     * @param userId
     * @param form
     * @return
     */
    PageList<OrderVO> getOrders(Integer userId, PageForm form);

    /**
     * 查看购物车列表
     * @param userId
     *
     * @return
     */
    PageList<CartVO> getCart(Integer userId);

    /**
     * 添加到购物车
     * @param userId
     * @param productId
     * @return
     */
    Integer addToCart(Integer userId,Integer productId);

    /**
     * 移除购物车
     * @param userId
     * @param cartId
     * @return
     */
    Integer removeFromCart(Integer userId,Integer cartId);

    /**
     * 获取收藏列表
     * @param userId
     * @return
     */
    PageList<CollectVO> getCollectList(Integer userId);

    /**
     * 添加到收藏
     * @param userId
     * @param productId
     * @return
     */
    Integer addToCollect(Integer userId,Integer productId);

    /**
     * 移除收藏
     * @param userId
     * @param collectId
     * @return
     */
    Integer removeFromCollects(Integer userId,Integer collectId);

    /**
     * 下订单
     * @param userId
     * @return
     */
    Integer createOrder(Integer userId, Map<Integer,Integer> orderForm);


    /**
     * 获取简单用户信息
     * @param userId
     * @return
     */
    SimpleUserInfo getSimpleUser(Integer userId);

    /**
     * 获取购物车中的商品数
     * @param userId
     * @return
     */
    Integer getCartNum(Integer userId);

    /**
     * 用户签收
     * @param userId
     * @param orderId
     * @return
     */
    Integer signOrder(Integer userId, Integer orderId);
}
