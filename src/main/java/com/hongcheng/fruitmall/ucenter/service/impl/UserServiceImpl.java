package com.hongcheng.fruitmall.ucenter.service.impl;

import com.hongcheng.fruitmall.common.beanMapper.BeanMapperFactory;
import com.hongcheng.fruitmall.common.exception.BusinessException;
import com.hongcheng.fruitmall.common.pojo.PageForm;
import com.hongcheng.fruitmall.common.pojo.PageList;
import com.hongcheng.fruitmall.common.pojo.PageQuery;
import com.hongcheng.fruitmall.common.pojo.SimpleUserInfo;
import com.hongcheng.fruitmall.mall.dao.mapper.FruitEntityMapper;
import com.hongcheng.fruitmall.mall.pojo.vo.SimpleFruitInfo;
import com.hongcheng.fruitmall.ucenter.dao.cache.UserCache;
import com.hongcheng.fruitmall.ucenter.dao.mapper.*;
import com.hongcheng.fruitmall.ucenter.pojo.entity.OrderEntity;
import com.hongcheng.fruitmall.ucenter.pojo.entity.UserAddressEntity;
import com.hongcheng.fruitmall.ucenter.pojo.entity.UserEntity;
import com.hongcheng.fruitmall.ucenter.pojo.vo.CartVO;
import com.hongcheng.fruitmall.ucenter.pojo.vo.CollectVO;
import com.hongcheng.fruitmall.ucenter.pojo.vo.OrderVO;
import com.hongcheng.fruitmall.ucenter.pojo.vo.UserInfoVO;
import com.hongcheng.fruitmall.ucenter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserEntityMapper userMapper;

    @Autowired
    private UserCache userCache;

    @Autowired
    private OrderEntityMapper orderMapper;

    @Autowired
    private OrderProductRelationMapper orderProductMapper;

    @Autowired
    private CartEntityMapper cartMapper;

    @Autowired
    private UserCollectEntityMapper collectMapper;

    @Autowired
    private UserAddressEntityMapper addressMapper;

    @Autowired
    private FruitEntityMapper fruitMapper;

    @Override
    public UserInfoVO getUserDetail(Integer userId) {
        checkUser(userId);
        return new UserInfoVO(addressMapper.selectByUserId(userId), userMapper.selectById(userId));
    }

    @Transactional(value = "transactionManager_fruitmall", rollbackFor = Exception.class)
    @Override
    public Integer updateUserInfo(Integer userId,UserInfoVO infoVO) {
        checkUser(userId);
        userMapper.update(infoVO.getUser(),userId);
        return addressMapper.update(infoVO.getAddress(),userId);
    }

    @Override
    public PageList<OrderVO> getOrders(Integer userId, PageForm form) {
        checkUser(userId);
        PageQuery qo = createQO(form);
        return new PageList<>(orderMapper.getCountByUserId(userId),
                orderMapper.selectDetailByUserId(userId,qo));
    }

    @Override
    public PageList<CartVO> getCart(Integer userId) {
        checkUser(userId);
        List<CartVO> list = cartMapper.getCartInfoByUserId(userId);
        return new PageList<>(list.size(), list);
    }

    @Override
    public PageList<CollectVO> getCollectList(Integer userId) {
        checkUser(userId);
        List<CollectVO> list = collectMapper.getCollectList(userId);
        return new PageList<>(list.size(),list);
    }

    @Override
    public Integer removeFromCart(Integer userId, Integer cartId) {
        checkUser(userId);
        return cartMapper.deleteById(cartId);
    }

    @Override
    public Integer removeFromCollects(Integer userId, Integer collectId) {
        checkUser(userId);
        return collectMapper.delete(collectId);
    }

    @Transactional(value = "transactionManager_fruitmall", rollbackFor = Exception.class)
    @Override
    public Integer createOrder(Integer userId, Map<Integer,Integer> orderForm) {
        checkUser(userId);
        //清空购物车
        cartMapper.deleteByUserId(userId);
        //提交Order
        return subOrder(orderForm, userId);
    }

    /**
     * 构造查询对象
     * @param form
     * @return
     */
    private PageQuery createQO(PageForm form) {
        PageQuery qo = BeanMapperFactory.getMapperFacade().map(form, PageQuery.class);
        qo.paging(form);
        return qo;
    }

    private void checkUser(Integer userId) {
        if (userId == null) {
            throw new BusinessException("还没有登录哦");
        }
    }

    private Integer subOrder(Map<Integer,Integer> orderForm,Integer userId) {
        OrderEntity order = new OrderEntity();
        order.setUserId(userId);
        order.setCreateTime(LocalDateTime.now());
        order.setState("submitted");
        UserAddressEntity address = addressMapper.selectByUserId(userId);
        if(address == null) {
            throw new BusinessException("没有地址");
        }
        if(address.getUserTelPhone() == null) {
            throw new BusinessException("没有电话");
        }
        //计算总额
        float pay = 0f;
        Map<Integer, SimpleFruitInfo> simpleFruits = fruitMapper.getBatchByIds(orderForm.keySet());
        for(Integer key : simpleFruits.keySet()) {
            Integer quantity = orderForm.get(key);
            SimpleFruitInfo info = simpleFruits.get(key);
            info.setQuantity(quantity);
            pay += (info.getDealPrice().floatValue()*quantity);
        }
        order.setPayMoney(BigDecimal.valueOf(pay));
        orderMapper.insert(order);
        //放入缓存队列
        OrderVO vo = BeanMapperFactory.getMapperFacade().map(order, OrderVO.class);
        vo.setFruitsInfo(new ArrayList<SimpleFruitInfo>(simpleFruits.values()));
        SimpleUserInfo userInfo = new SimpleUserInfo();
        UserEntity user = userMapper.selectById(userId);
        userInfo.setName(user.getRealname());
        userInfo.setEmail(user.getEmail());
        userInfo.setUserTelPhone(address.getUserTelPhone());
        vo.setUserInfo(userInfo);
        userCache.pushOrderToQueue(vo);
        return orderProductMapper.insertBatch(simpleFruits.values(),order.getOrderId());
    }
}
