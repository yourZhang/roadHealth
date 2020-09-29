package cn.zys.service.impl;

import cn.zys.common.MessageConst;
import cn.zys.exception.BusinessRuntimeException;
import cn.zys.mapper.MemberMapper;
import cn.zys.mapper.OrderMapper;
import cn.zys.mapper.OrderSettingMapper;
import cn.zys.pojo.Member;
import cn.zys.pojo.Order;
import cn.zys.pojo.OrderSetting;
import cn.zys.pojo.Setmeal;
import cn.zys.service.OrderService;
import cn.zys.service.SetMealService;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: road-health
 * @description:
 * @author: xiaozhang6666
 * @create: 2020-09-29 17:03
 **/
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    OrderSettingMapper orderSettingMapper;

    @Autowired
    MemberMapper memberMapper;

    @Autowired
    SetMealService setMealService;

    @Override
    @Transactional(transactionManager = "transactionManager", isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public Order saveOrder(Order order) {
        //业务
        //插入订单之前先 查询是否可以预约今天，即管理员是否有设置预约信息
        final OrderSetting byDate = orderSettingMapper.findByDate(order.getOrderDate());
        if (null == byDate) {
            //说明管理员没有设置 预约信息
            throw new BusinessRuntimeException(MessageConst.SELECTED_DATE_CANNOT_ORDER);
        } else if (byDate.getReservations() >= byDate.getNumber()) {
            //如果存在-且约满
            throw new BusinessRuntimeException(MessageConst.ORDER_FULL);
        }
        //查询是否有 同一个人 预约了同一天的 相同内容 的 订单
        final Integer byDateAndOrderAndMemberId = orderMapper.findByDateAndOrderAndMemberId(
                order.getMemberId(), order.getOrderDate(), order.getSetmealId()
        );
        if (byDateAndOrderAndMemberId > 0) {
            throw new BusinessRuntimeException(MessageConst.HAS_ORDERED);
        }
        orderMapper.saveOrder(order);
        //查询相关order详情 并返回
        final Order byIdOne = orderMapper.findByIdOne(order.getId());
        return byIdOne;
    }

    @Override
    public Map<String, Object> findById(Integer id) {
        //获取到用户id
        final Order OrderbById = orderMapper.findById(id);
        //通过用户id获取用户信息
        final Member MemberById = memberMapper.findById(OrderbById.getMemberId());
        //获取到套餐信息
        final Setmeal byIdOne = setMealService.findByIdOne(OrderbById.getSetmealId());
        Map<String, Object> map = new HashMap<>();
        map.put("member", MemberById.getName());
        map.put("setmeal", byIdOne.getName());
        map.put("orderDate", new SimpleDateFormat("yyyy-MM-dd").format(OrderbById.getOrderDate()));
        map.put("orderType", OrderbById.getOrderType());
        return map;
    }
}
