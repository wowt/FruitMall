package com.hongcheng.fruitmall.ucenter.dao.cache;

import com.hongcheng.fruitmall.ucenter.pojo.vo.OrderVO;
import org.springframework.stereotype.Component;

import com.hongcheng.fruitmall.common.cache.AbstractCache;

@Component
public class UserCache extends AbstractCache {

    private static final String orderQueueKey = "order_queue_key";

    public void pushOrderToQueue(OrderVO vo) {
        pushFromTail(orderQueueKey,vo);
    }
}
