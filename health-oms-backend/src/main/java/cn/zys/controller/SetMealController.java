package cn.zys.controller;

import cn.zys.common.MessageConst;
import cn.zys.common.RedisMessage;
import cn.zys.entity.PageResult;
import cn.zys.entity.QueryPageBean;
import cn.zys.entity.Result;
import cn.zys.pojo.Setmeal;
import cn.zys.service.SetMealService;
import cn.zys.utils.QiniuUtils;
import com.alibaba.dubbo.config.annotation.Reference;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * @program: road-health
 * @description:
 * @author: xiaozhang6666
 * @create: 2020-09-25 17:45
 **/
@RestController
@RequestMapping("meal")
@Slf4j
public class SetMealController {

    @Reference
    SetMealService setMealService;

    @Autowired
    JedisPool jedisPool;

    @RequestMapping("findPage")
    public Result findPage(QueryPageBean queryPageBean) {
        log.info("分页查询:::{}", queryPageBean);
        PageResult page = setMealService.findPage(queryPageBean);
        return new Result(true, MessageConst.ACTION_SUCCESS, page);
    }

    /**
     * 功能描述: <br>
     * 〈添加新套餐〉
     *
     * @Param: [setmeal, groupId]
     * @return: cn.zys.entity.Result
     * @Author: xiaozhang666
     * @Date: 2020/9/25 20:44
     */
    @RequestMapping("add/{groupId}")
    public Result save(Setmeal setmeal, @PathVariable Integer[] groupId) {
        Result result = setMealService.addMeal(setmeal, groupId);
        return result;
    }

    /**
     * 功能描述: <br>
     * 〈上传图片〉
     *
     * @Param: [multipartFile]
     * @return: cn.zys.entity.Result
     * @Author: xiaozhang666
     * @Date: 2020/9/25 20:44
     */
    @RequestMapping("uploadImg")
    public Result uploadImg(@RequestParam("imgFile") MultipartFile multipartFile) {
        log.info("上传文件的名:{}，大小:{}", multipartFile.getOriginalFilename(), multipartFile.getSize());
        //原始文件名
        String originalFileName = multipartFile.getOriginalFilename();
        //使用UUID构造不重复的文件名
        String fileName = UUID.randomUUID().toString().replace("-", "") + "_" + originalFileName;
        try (InputStream inputStream = multipartFile.getInputStream()) {
            QiniuUtils.upload2Qiniu(inputStream, fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (final Jedis resource = jedisPool.getResource()) {
            resource.sadd(RedisMessage.ImageOutRedisName, fileName);
        }
        return new Result(true, MessageConst.ACTION_SUCCESS, fileName);
    }
}
