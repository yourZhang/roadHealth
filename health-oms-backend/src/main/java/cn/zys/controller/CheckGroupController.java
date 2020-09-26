package cn.zys.controller;

import cn.zys.common.MessageConst;
import cn.zys.entity.PageResult;
import cn.zys.entity.QueryPageBean;
import cn.zys.entity.Result;
import cn.zys.pojo.CheckGroup;
import cn.zys.pojo.CheckItem;
import cn.zys.service.CheckGroupService;
import com.alibaba.dubbo.config.annotation.Reference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: road-health
 * @description:
 * @author: xiaozhang6666
 * @create: 2020-09-24 15:31
 **/
@RestController
@RequestMapping("group")
@Slf4j
public class CheckGroupController {
    @Reference
    private CheckGroupService checkGroupService;

    /**
     * 功能描述: <br>
     * 〈查询所有检查组 分页 条件检索〉
     *
     * @Param: [queryPageBean]
     * @return: cn.zys.entity.Result
     * @Author: xiaozhang666
     * @Date: 2020/9/24 16:01
     */
    @RequestMapping("findPage")
    public Result findPage(QueryPageBean queryPageBean) {
        log.info(":::{}", queryPageBean);
        PageResult group = checkGroupService.findGroup(queryPageBean);
        return new Result(true, MessageConst.ACTION_SUCCESS, group);
    }

    /**
     * 功能描述: <br>
     * 〈查询  添加和更新的检查项〉
     *
     * @Param: []
     * @return: cn.zys.entity.Result
     * @Author: xiaozhang666
     * @Date: 2020/9/24 16:17
     */
    @RequestMapping("findAllItem")
    public Result findAllItem() {
        List<CheckItem> allItem = checkGroupService.findAllItem();
        return new Result(true, MessageConst.ACTION_SUCCESS, allItem);
    }

    /**
     * 功能描述: <br>
     * 〈添加检查组 和 检查项关联表〉
     *
     * @Param: [checkGroup, itemId]
     * @return: cn.zys.entity.Result
     * @Author: xiaozhang666
     * @Date: 2020/9/24 17:34
     */
    @RequestMapping("save/{itemId}")
    public Result save(CheckGroup checkGroup, @PathVariable Integer[] itemId) {
        log.info(":::检查组的添加参数:::{}", checkGroup);
        log.info(":::检查项的相关id:::{}", itemId);
        Result save = checkGroupService.save(checkGroup, itemId);
        return save;
    }

    /**
     * 功能描述: <br>
     * 〈回显需要更新的数据〉
     *
     * @Param: [id]
     * @return: cn.zys.entity.Result
     * @Author: xiaozhang666
     * @Date: 2020/9/24 20:16
     */
    @RequestMapping("findById/{id}")
    public Result findById(@PathVariable Integer id) {
        log.info(":::回显id:::{}", id);
        CheckGroup byId = checkGroupService.findById(id);
        return new Result(true, MessageConst.ACTION_SUCCESS, byId);
    }

    /**
     * 功能描述: <br>
     * 〈回显需要 跟新的 检查项id〉
     *
     * @Param: [id]
     * @return: cn.zys.entity.Result
     * @Author: xiaozhang666
     * @Date: 2020/9/24 20:17
     */
    @RequestMapping("groupIdAndItemId/{id}")
    public Result groupIdAndItemId(@PathVariable Integer id) {
        List<Integer> integers = checkGroupService.groupIdAndItemId(id);
        return new Result(true, MessageConst.ACTION_SUCCESS, integers);
    }

    /**
     * 功能描述: <br>
     * 〈更新和编译检查组〉
     *
     * @Param: [checkGroup, itemId]
     * @return: cn.zys.entity.Result
     * @Author: xiaozhang666
     * @Date: 2020/9/25 17:58
     */
    @RequestMapping("update/{itemId}")
    public Result update(CheckGroup checkGroup, @PathVariable("itemId") Integer[] itemId) {
        log.info(":::更新内容{}:::关系项{}", checkGroup, itemId);
        return checkGroupService.update(checkGroup, itemId);
    }

    @RequestMapping("findAllGroup")
    public Result findAllGroup() {
        List<CheckGroup> allGroup = checkGroupService.findAllGroup();
        return new Result(true, MessageConst.ACTION_SUCCESS, allGroup);
    }
}
