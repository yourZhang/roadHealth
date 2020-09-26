package cn.zys.mapper;

import cn.zys.entity.QueryPageBean;
import cn.zys.entity.Result;
import cn.zys.pojo.Setmeal;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;


public interface SetMealMapper {
    //展示数据表格 分页条件
    Page<Setmeal> findPage(QueryPageBean queryPageBean);

    //添加套餐
    Integer addMeal(Setmeal setmeal);

    //添加套餐和检查组关系表
    Integer addMealAndGroupId(@Param("groupId") Integer[] groupId, @Param("setmeal_id") Integer setmeal_id);
}
