package cn.zys.mapper;


import cn.zys.entity.QueryPageBean;
import cn.zys.entity.Result;
import cn.zys.pojo.CheckItem;
import com.github.pagehelper.Page;

public interface CheckItemMapper {
    //分页查询所有
    Page<CheckItem> QueryAll(QueryPageBean queryPageBean);
    //添加
    Integer addItem(CheckItem checkItem);
    //回显更新数据
    CheckItem oneUpdate(Integer id);
    //更新一条数据
    Integer updateCheck(CheckItem checkItem);
    //删除一条
    Integer delItem(Integer id);
    //查询检查项有无关联的检查组
    Long findGroups(Integer id);
}
