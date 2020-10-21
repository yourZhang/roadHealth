package cn.zys.service.impl;

import cn.zys.entity.Result;
import cn.zys.mapper.PermissionMapper;
import cn.zys.mapper.RoleMapper;
import cn.zys.mapper.UserMapper;
import cn.zys.pojo.Permission;
import cn.zys.pojo.Role;
import cn.zys.pojo.TestUser;
import cn.zys.pojo.User;
import cn.zys.pojoVo.UserVo;
import cn.zys.service.UserService;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @program: road-health
 * @description:
 * @author: xiaozhang6666
 * @create: 2020-09-20 22:46
 **/
@Service
@org.springframework.stereotype.Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    RoleMapper roleMapper;

    @Autowired
    PermissionMapper permissionMapper;

    @Override
    public Result login(UserVo userVo) {
        Integer login = userMapper.login(userVo);
        if (login != 1) {
            return new Result(false, "用户名密码错误！！");
        }
        return new Result(true, "ok");
    }

    @Override
    public List<TestUser> findAll() {
        System.out.println(userMapper + "=======================");
        PageHelper.startPage(1, 3);
        Page<TestUser> pageList = userMapper.findAll();
//        List<TestUser> list = userMapper.findAll();
//        PageInfo<TestUser> pageInfo = new PageInfo<>(list);
//        System.out.println(pageInfo + "+++++");
        return pageList.getResult();
    }

    @Override
    @Transactional(transactionManager = "transactionManager", isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public Integer saveUser(TestUser user) {
        Integer integer = userMapper.saveUser(user);
        int i = 1 / 0;
        return integer;
    }

    @Override
    public User findByUsername(String username) {
        final User user = userMapper.selectByUsername(username);
        log.info(":::查看数据{}", user);
        //查询角色
        List<Role> roles = roleMapper.selectByUserId(user.getId());
        roles.forEach((values) -> {
            List<Permission> byRoleId = permissionMapper.findByRoleId(values.getId());
            values.getPermissions().addAll(byRoleId);
        });
        user.getRoles().addAll(roles);
        return user;
    }
}
