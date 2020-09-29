package cn.zys.service.impl;

import cn.zys.common.MessageConst;
import cn.zys.common.RedisMessage;
import cn.zys.entity.PageResult;
import cn.zys.entity.QueryPageBean;
import cn.zys.entity.Result;
import cn.zys.mapper.SetMealMapper;
import cn.zys.pojo.Setmeal;
import cn.zys.service.SetMealService;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;


/**
 * @program: road-health
 * @description:
 * @author: xiaozhang6666
 * @create: 2020-09-25 17:45
 **/
@Service
public class SetMealServiceImpl implements SetMealService {

    @Autowired
    SetMealMapper setMealMapper;

    @Autowired
    JedisPool jedisPool;

    @Override
    public PageResult findPage(QueryPageBean queryPageBean) {
        PageHelper.startPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());
        Page<Setmeal> page = setMealMapper.findPage(queryPageBean);
        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    @Transactional(transactionManager = "transactionManager", isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public Result addMeal(Setmeal setmeal, Integer[] groupId) {
        Integer result = setMealMapper.addMeal(setmeal);
        Integer integer = setMealMapper.addMealAndGroupId(groupId, setmeal.getId());
        if (!StringUtils.isEmpty(setmeal.getImg())) {
            try (final Jedis resource = jedisPool.getResource()) {
                resource.sadd(RedisMessage.ImageInRedisName, setmeal.getImg());
            }
        }
        return new Result(true, MessageConst.ACTION_SUCCESS, integer);
    }

    @Override
    public Result getSetmeal() {
        final List<Setmeal> setmeal = setMealMapper.getSetmeal();
        return new Result(true, MessageConst.GET_ORDERSETTING_SUCCESS, setmeal);
    }

    @Override
    public Result findById(Integer id) {
        final Setmeal byId = setMealMapper.findById(id);
        return new Result(true, MessageConst.GET_SETMEAL_COUNT_REPORT_SUCCESS, byId);
    }

    @Override
    public Setmeal findByIdOne(Integer id) {
        final Setmeal byIdOne = setMealMapper.findByIdOne(id);
        return byIdOne;
    }
}
