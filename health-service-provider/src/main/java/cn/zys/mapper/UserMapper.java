package cn.zys.mapper;

import cn.zys.entity.Result;
import cn.zys.pojo.TestUser;
import cn.zys.pojoVo.UserVo;
import com.github.pagehelper.Page;

/**
 * @program: road-health
 * @description: UserMapper
 * @author: xiaozhang6666
 * @create: 2020-09-20 22:41
 **/
public interface UserMapper {
    //登陆
    Integer login(UserVo userVo);
    //查询所有
    Page<TestUser> findAll();
    //测试事务
    Integer saveUser(TestUser user);
}
