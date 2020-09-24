package cn.zys.service;

import cn.zys.entity.PageResult;
import cn.zys.entity.QueryPageBean;
import cn.zys.entity.Result;
import cn.zys.pojo.CheckGroup;
import cn.zys.pojo.CheckItem;

import java.util.List;

public interface CheckGroupService {
    //查询列表
    PageResult findGroup(QueryPageBean queryPageBean);
    //查询所有检查项 数据回显
    List<CheckItem> findAllItem();
    //插入一提奥检查组
    Result save(CheckGroup checkGroup, Integer[] itemId);
    //更新的数据回显
    CheckGroup findById(Integer id);
    //回显关系表
    List<Integer> groupIdAndItemId(Integer id);
    //更新数据
    Result update(CheckGroup checkGroup, Integer[] itemId);
}
