package cn.zys.service.impl;

import cn.zys.common.MessageConst;
import cn.zys.entity.PageResult;
import cn.zys.entity.QueryPageBean;
import cn.zys.entity.Result;
import cn.zys.exception.BusinessRuntimeException;
import cn.zys.mapper.CheckItemMapper;
import cn.zys.pojo.CheckItem;
import cn.zys.service.CheckItemService;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @program: road-health
 * @description:
 * @author: xiaozhang6666
 * @create: 2020-09-22 16:30
 **/
@Service
public class CheckItemServiceImpl implements CheckItemService {

    @Autowired
    CheckItemMapper checkItemMapper;

    @Override
    public PageResult QueryAll(QueryPageBean queryPageBean) {
        PageHelper.startPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());
        Page<CheckItem> checkItems = checkItemMapper.QueryAll(queryPageBean);
        return new PageResult(checkItems.getTotal(), checkItems.getResult());
    }

    @Override
    @Transactional(transactionManager = "transactionManager", isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public Integer addItem(CheckItem checkItem) {
        Integer integer = checkItemMapper.addItem(checkItem);
        return integer;
    }

    @Override
    public CheckItem oneUpdate(Integer id) {
        CheckItem result = checkItemMapper.oneUpdate(id);
        return result;
    }

    @Override
    @Transactional(transactionManager = "transactionManager", isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public Result updateCheck(CheckItem checkItem) {
        Integer integer = checkItemMapper.updateCheck(checkItem);
        return new Result(true, MessageConst.EDIT_CHECKITEM_SUCCESS, integer);
    }

    @Override
    @Transactional(transactionManager = "transactionManager", isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public Result delItem(Integer id) {
        //先查询有无关联的 检查组
        Long groups = checkItemMapper.findGroups(id);
        if(groups > 0){
            throw new BusinessRuntimeException("存在关联项，删除失败");
        }else{
            Integer integer = checkItemMapper.delItem(id);
            return new Result(true, MessageConst.DELETE_CHECKITEM_SUCCESS, integer);
        }
    }
}
