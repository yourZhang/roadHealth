package cn.zys.service;

import cn.zys.entity.PageResult;
import cn.zys.entity.QueryPageBean;
import cn.zys.entity.Result;
import cn.zys.pojo.CheckItem;

public interface CheckItemService {
    //分页查询所有
    PageResult QueryAll(QueryPageBean queryPageBean);
    //添加
    Integer addItem(CheckItem checkItem);
    //回显更新数据
    CheckItem oneUpdate(Integer id);
    //更新一条数据
    Result updateCheck(CheckItem checkItem);
    //删除一条
    Result delItem(Integer id);
}
