package cn.zys.mapper;

import cn.zys.entity.QueryPageBean;
import cn.zys.entity.Result;
import cn.zys.pojo.Setmeal;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface SetMealMapper {
    //展示数据表格 分页条件
    Page<Setmeal> findPage(QueryPageBean queryPageBean);

    //添加套餐
    Integer addMeal(Setmeal setmeal);

    //添加套餐和检查组关系表
    Integer addMealAndGroupId(@Param("groupId") Integer[] groupId, @Param("setmeal_id") Integer setmeal_id);

    //查询所有预约内容
    List<Setmeal> getSetmeal();

    //查询预约详情 和所有的关联项关系表
    Setmeal findById(Integer id);

    //查询单个setmeal
    Setmeal findByIdOne(Integer id);
}
