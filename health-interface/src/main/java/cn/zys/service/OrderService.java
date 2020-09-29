package cn.zys.service;

import cn.zys.pojo.Order;

import java.util.Map;

public interface OrderService {
    //插入订单信息
    Order saveOrder(Order order);

    //查询 成功页的相关数据
    Map<String,Object> findById(Integer id);
}
