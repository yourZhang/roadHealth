package cn.zys.mapper;

import cn.zys.entity.QueryPageBean;
import cn.zys.pojo.CheckGroup;
import cn.zys.pojo.CheckItem;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CheckGroupMapper {
    //查询列表
    Page<CheckGroup> findGroup(QueryPageBean queryPageBean);

    //查询所有检查项 数据回显
    List<CheckItem> findAllItem();

    //插入一条检查项
    Integer save(CheckGroup checkGroup);

    //插入检查项和检查组的 关系表
    Integer saveItemId(@Param("itemId") Integer[] itemId, @Param("groupId") Integer groupId);

    //更新的数据回显
    CheckGroup findById(Integer id);

    //回显关系表
    List<Integer> groupIdAndItemId(Integer id);

    //更新数据
    Integer update(CheckGroup checkGroup);

    //删除关系表
    Integer delIds(Integer id);

    //查询所有检查组
    List<CheckGroup> findAllGroup();
}
