package cn.zys.service.impl;

import cn.zys.common.MessageConst;
import cn.zys.entity.Result;
import cn.zys.mapper.OrderSettingMapper;
import cn.zys.pojo.OrderSetting;
import cn.zys.service.OrderSettingService;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/**
 * @program: road-health
 * @description:
 * @author: xiaozhang6666
 * @create: 2020-09-26 15:58
 **/
@Service
public class OrderSettingServiceImpl implements OrderSettingService {

    @Autowired
    OrderSettingMapper orderSettingMapper;

    @Override
    public Result saveOrders(List<OrderSetting> orderSettings) {
        Integer integer = orderSettingMapper.saveOrders(orderSettings);
        return new Result(true, MessageConst.IMPORT_ORDERSETTING_SUCCESS, integer);
    }

    @Override
    public Result findAll(Integer year, Integer month) {
        String startYear = String.format("%d-%d-01", year, month);
        String endYear = String.format("%d-%d-31", year, month);
        final List<OrderSetting> all = orderSettingMapper.findAll(startYear, endYear);
        //处理数据格式
        List<Map<String, Object>> list = new ArrayList<>();
        //取到前端用的日期中的天
        all.forEach((item) -> {
            Map<String, Object> map = new HashMap<>();
            Calendar c = Calendar.getInstance();
            c.setTime(item.getOrderDate());
            map.put("date", c.get(Calendar.DAY_OF_MONTH));
            map.put("number", item.getNumber());
            map.put("reservations", item.getReservations());
            list.add(map);
        });
        return new Result(true, MessageConst.ACTION_SUCCESS, list);
    }

    @Override
    public Result editNumberByDate(OrderSetting orderSetting) {
        final Integer byId = orderSettingMapper.findById(orderSetting);
        orderSetting.setId(byId);
        final Integer integer = orderSettingMapper.editNumberByDate(orderSetting);
        return new Result(true, MessageConst.ORDERSETTING_SUCCESS, integer);
    }
}
