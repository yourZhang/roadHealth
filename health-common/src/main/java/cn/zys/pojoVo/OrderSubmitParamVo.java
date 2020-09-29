package cn.zys.pojoVo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @program: road-health
 * @description: 接收订单操作相关
 * @author: xiaozhang6666
 * @create: 2020-09-29 16:46
 **/
@Data
public class OrderSubmitParamVo {
    private String name;
    private String sex;
    private String telephone;
    private String validateCode;
    private String idCard;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date orderDate;
    private Integer setMealId;
}
