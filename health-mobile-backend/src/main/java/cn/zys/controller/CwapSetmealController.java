package cn.zys.controller;

import cn.zys.common.MessageConst;
import cn.zys.entity.Result;
import cn.zys.pojo.Setmeal;
import cn.zys.service.SetMealService;
import com.alibaba.dubbo.config.annotation.Reference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: road-health
 * @description: 客户端预约接口
 * @author: xiaozhang6666
 * @create: 2020-09-28 15:20
 **/
@RestController
@RequestMapping("cwap/setmeal")
@Slf4j
public class CwapSetmealController {

    @Reference
    SetMealService setMealService;

    /**
     * 功能描述: <br>
     * 〈查询所有预约对象〉
     *
     * @Param: []
     * @return: cn.zys.entity.Result
     * @Author: xiaozhang666
     * @Date: 2020/9/28 15:37
     */
    @RequestMapping("getSetmeal")
    public Result getSetmeal() {
        return setMealService.getSetmeal();
    }

    /**
     * 功能描述: <br>
     * 〈根据setmeal 的 id查询 关联的 group item〉
     *
     * @Param: [id]
     * @return: cn.zys.entity.Result
     * @Author: xiaozhang666
     * @Date: 2020/9/28 16:49
     */
    @RequestMapping("findById/{id}")
    public Result findById(@PathVariable Integer id) {
        log.info("获取id:::{}", id);
        return setMealService.findById(id);
    }

    @RequestMapping("findByIdOne/{id}")
    public Result findByIdOne(@PathVariable Integer id) {
        log.info("获取id:::{}", id);
        final Setmeal byIdOne = setMealService.findByIdOne(id);
        return new Result(true, MessageConst.ACTION_SUCCESS, byIdOne);
    }
}
