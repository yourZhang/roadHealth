package cn.zys.controller;

import cn.zys.common.MessageConst;
import cn.zys.entity.PageResult;
import cn.zys.entity.QueryPageBean;
import cn.zys.entity.Result;
import cn.zys.pojo.CheckItem;
import cn.zys.service.CheckItemService;
import com.alibaba.dubbo.config.annotation.Reference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @program: road-health
 * @description: CheckItemController
 * @author: xiaozhang6666
 * @create: 2020-09-22 16:25
 **/
@Controller
@RequestMapping("item")
@Slf4j
public class CheckItemController {

    @Reference
    CheckItemService checkItemService;

    /**
     * 功能描述: <br>
     * 〈搜索 分页等〉
     *
     * @Param: [queryPageBean]
     * @return: cn.zys.entity.Result
     * @Author: xiaozhang666
     * @Date: 2020/9/22 17:43
     */
    @RequestMapping("queryItem")
    @ResponseBody
    public Result queryItem(QueryPageBean queryPageBean) {
        PageResult pageResult = checkItemService.QueryAll(queryPageBean);
        return new Result(true, "查询成功", pageResult);
    }

    /**
     * 功能描述: <br>
     * 〈插入单个检查项〉
     *
     * @Param: [checkItem]
     * @return: cn.zys.entity.Result
     * @Author: xiaozhang666
     * @Date: 2020/9/22 19:42
     */
    @RequestMapping("addItem")
    @ResponseBody
    public Result addItem(CheckItem checkItem) {
        log.info(":::", checkItem);
        Integer integer = checkItemService.addItem(checkItem);
        return new Result(true, MessageConst.ADD_CHECKITEM_SUCCESS, integer);
    }

    /**
     * 功能描述: <br>
     * 〈修改前回显数据〉
     *
     * @Param: [id]
     * @return: cn.zys.entity.Result
     * @Author: xiaozhang666
     * @Date: 2020/9/22 20:25
     */
    @RequestMapping("oneUpdate/{id}")
    @ResponseBody
    public Result oneUpdate(@PathVariable("id") Integer id) {
        log.info(":::", id);
        CheckItem checkItem = checkItemService.oneUpdate(id);
        return new Result(true, "最新数据回显", checkItem);
    }

    /**
     * 功能描述: <br>
     * 〈更新 检查项 操作〉
     *
     * @Param: [checkItem]
     * @return: cn.zys.entity.Result
     * @Author: xiaozhang666
     * @Date: 2020/9/23 16:56
     */
    @RequestMapping("updateCheck")
    @ResponseBody
    public Result updateCheck(CheckItem checkItem) {
        log.info(":::{}", checkItem);
        return checkItemService.updateCheck(checkItem);
    }

    /**
     * 功能描述: <br>
     * 〈删除一个 检查项〉
     *
     * @Param: [id]
     * @return: cn.zys.entity.Result
     * @Author: xiaozhang666
     * @Date: 2020/9/24 8:34
     */
    @RequestMapping("delItem/{id}")
    @ResponseBody
    public Result delItem(@PathVariable Integer id) {
        return checkItemService.delItem(id);
    }
}
