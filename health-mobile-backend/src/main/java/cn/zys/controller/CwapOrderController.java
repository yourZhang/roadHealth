package cn.zys.controller;

import cn.zys.common.MessageConst;
import cn.zys.entity.Result;
import cn.zys.pojo.Order;
import cn.zys.pojo.Member;
import cn.zys.pojoVo.OrderSubmitParamVo;
import cn.zys.service.MemberService;
import cn.zys.service.OrderService;
import cn.zys.service.SmsAliService;
import com.alibaba.dubbo.config.annotation.Reference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Map;

/**
 * @program: road-health
 * @description: 用户订单操作
 * @author: xiaozhang6666
 * @create: 2020-09-29 16:44
 **/
@RestController
@RequestMapping("cwap/order")
@Slf4j
public class CwapOrderController {

    @Reference
    SmsAliService smsAliService;
    @Reference
    OrderService orderService;
    @Reference
    MemberService memberService;


    @RequestMapping("saveOrder")
    public Result saveOrder(OrderSubmitParamVo orderSubmitParamVo) {
        log.info("参数打印：：：{}", orderSubmitParamVo);
        final Boolean order = smsAliService.checkValidateCode("ORDER", orderSubmitParamVo.getTelephone(), orderSubmitParamVo.getValidateCode());
        if (!order) {
            return new Result(order, MessageConst.VALIDATECODE_ERROR);
        }
        //2-创建会员 memberService
        Member member = new Member();
        member.setIdCard(orderSubmitParamVo.getIdCard());
        member.setRegTime(new Date());
        member.setPhoneNumber(orderSubmitParamVo.getTelephone());
        member.setName(orderSubmitParamVo.getName());
        member.setSex(orderSubmitParamVo.getSex());
        member = memberService.saveMember(member);

        //3-提交体检信息  orderService
        Order orders = new Order();
        orders.setOrderStatus(Order.ORDERSTATUS_NO);
        orders.setSetmealId(orderSubmitParamVo.getSetMealId());
        orders.setOrderType(Order.ORDERTYPE_WEIXIN);
        orders.setOrderDate(orderSubmitParamVo.getOrderDate());
        orders.setMemberId(member.getId());
        final Order order1 = orderService.saveOrder(orders);

        return new Result(true, MessageConst.ACTION_SUCCESS, order1);
    }

    @RequestMapping("findById/{id}")
    public Result findById(@PathVariable Integer id) {
        log.info(":::::{}:::::::::::::::::::::::::::::::::::", id);
        final Map<String, Object> byId = orderService.findById(id);
        return new Result(true, MessageConst.ORDER_SUCCESS, byId);
    }
}
