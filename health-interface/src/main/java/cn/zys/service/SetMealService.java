package cn.zys.service;

import cn.zys.entity.PageResult;
import cn.zys.entity.QueryPageBean;
import cn.zys.entity.Result;
import cn.zys.pojo.Setmeal;

public interface SetMealService {
    //展示数据表格 分页条件
    PageResult findPage(QueryPageBean queryPageBean);
    //添加套餐
    Result addMeal(Setmeal setmeal,Integer[] groupId);
}
