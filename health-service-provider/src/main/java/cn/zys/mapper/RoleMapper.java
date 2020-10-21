package cn.zys.mapper;

import cn.zys.pojo.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {
    /**
     * 根据用户ID查询
     */
    List<Role> selectByUserId(@Param("userId") Integer userId);
}
