package cn.zys.mapper;

import cn.zys.pojo.Member;
import cn.zys.pojo.Order;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.Map;

public interface OrderMapper {
    //插入订单信息
    Integer saveOrder(Order order);

    //查询是否有 同一个人 预约了同一天的 相同内容 的 订单
    Integer findByDateAndOrderAndMemberId(@Param("mid") Integer mid, @Param("date") Date date, @Param("sid") Integer sid);

    //查询单个详情
    Order findByIdOne(Integer id);

    //查询 成功页的相关数据
    Order findById(Integer id);
}
