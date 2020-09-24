package cn.zys.controller;

import cn.zys.entity.Result;
import cn.zys.pojo.TestUser;
import cn.zys.pojoVo.UserVo;
import cn.zys.service.UserService;
import com.alibaba.dubbo.config.annotation.Reference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @program: road-health
 * @description: UserController
 * @author: xiaozhang6666
 * @create: 2020-09-21 16:21
 **/
//@CrossOrigin(origins = "http://127.0.0.1:8083", allowCredentials = "true")
@Controller
@RequestMapping("user")
@Slf4j
public class UserController {

    @Reference
    UserService userService;

    @RequestMapping("login")
    @ResponseBody
    public Result login(UserVo userVo) {
        log.info("：：：" + userVo);
        Result login = userService.login(userVo);
        return login;
    }

    //测试中文字符
    @RequestMapping("findAllU")
    @ResponseBody
    public String findAllU() {
        return "你好";
    }

    //测试dubbo注册调用
    @RequestMapping("findAll")
    @ResponseBody
    public Result findAll() {
        List<TestUser> all = userService.findAll();
        return new Result(true, "成功", all);
    }
}
