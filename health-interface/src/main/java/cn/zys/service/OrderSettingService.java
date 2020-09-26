package cn.zys.service;

import cn.zys.entity.Result;
import cn.zys.pojo.OrderSetting;

import java.util.List;

public interface OrderSettingService {
    //批量导入插入预约数据
    Result saveOrders(List<OrderSetting> orderSettings);

    //查询当前月份内容
    Result findAll(Integer year, Integer month);

    //按照日期修改预约数量
    Result editNumberByDate(OrderSetting orderSetting);
}
