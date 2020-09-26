package cn.zys.mapper;

import cn.zys.entity.Result;
import cn.zys.pojo.OrderSetting;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderSettingMapper {
    //批量导入插入预约数据
    Integer saveOrders(List<OrderSetting> orderSettings);

    //查询当前月份内容
    List<OrderSetting> findAll(@Param("startYear") String startYear, @Param("endYear") String endYear);

    //按照日期修改预约数量
    Integer editNumberByDate(OrderSetting orderSetting);

    //更新之前获取id，根据时间查询
    Integer findById(OrderSetting orderSetting);
}
