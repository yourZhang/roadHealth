package cn.zys.mapper;

import cn.zys.pojo.Permission;

import java.util.List;

public interface PermissionMapper {
    //根据 id 查询权限
    List<Permission> findByRoleId(Integer id);
}
