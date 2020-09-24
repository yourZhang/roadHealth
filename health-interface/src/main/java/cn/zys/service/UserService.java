package cn.zys.service;

import cn.zys.entity.Result;
import cn.zys.pojo.TestUser;
import cn.zys.pojoVo.UserVo;

import java.util.List;

public interface UserService {
    //用户登录
    Result login(UserVo userVo);
    //查询所有
    List<TestUser> findAll();
    //测试事务
    Integer saveUser(TestUser user);
}
