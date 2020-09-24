package cn.zys.service.impl;

import cn.zys.common.MessageConst;
import cn.zys.entity.PageResult;
import cn.zys.entity.QueryPageBean;
import cn.zys.entity.Result;
import cn.zys.mapper.CheckGroupMapper;
import cn.zys.pojo.CheckGroup;
import cn.zys.pojo.CheckItem;
import cn.zys.service.CheckGroupService;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @program: road-health
 * @description:
 * @author: xiaozhang6666
 * @create: 2020-09-24 15:31
 **/
@Service
public class CheckGroupServiceImpl implements CheckGroupService {

    @Autowired
    private CheckGroupMapper checkGroupMapper;

    @Override
    public PageResult findGroup(QueryPageBean queryPageBean) {
        PageHelper.startPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());
        Page<CheckGroup> group = checkGroupMapper.findGroup(queryPageBean);
        return new PageResult(group.getTotal(), group.getResult());
    }

    @Override
    public List<CheckItem> findAllItem() {
        List<CheckItem> allItem = checkGroupMapper.findAllItem();
        return allItem;
    }

    @Override
    @Transactional(transactionManager = "transactionManager", isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public Result save(CheckGroup checkGroup, Integer[] itemId) {
        Integer save = checkGroupMapper.save(checkGroup);
        //根据主键返回id 插入关系表
        Integer integer = checkGroupMapper.saveItemId(itemId, checkGroup.getId());
        return new Result(true, MessageConst.ADD_CHECKGROUP_SUCCESS, integer);
    }

    @Override
    public CheckGroup findById(Integer id) {
        CheckGroup byId = checkGroupMapper.findById(id);
        return byId;
    }

    @Override
    public List<Integer> groupIdAndItemId(Integer id) {
        List<Integer> integers = checkGroupMapper.groupIdAndItemId(id);
        return integers;
    }

    @Override
    @Transactional(transactionManager = "transactionManager", isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public Result update(CheckGroup checkGroup, Integer[] itemId) {
        Integer update = checkGroupMapper.update(checkGroup);
        //删除所有关系并重新建立
        //先删除
        Integer integer = checkGroupMapper.delIds(checkGroup.getId());
        //重新建立
        Integer integer1 = checkGroupMapper.saveItemId(itemId, checkGroup.getId());
        return new Result(true,MessageConst.EDIT_CHECKGROUP_SUCCESS,integer1);
    }
}
