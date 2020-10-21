package cn.zys.mapper;

import cn.zys.pojo.Member;
import org.apache.ibatis.annotations.Param;

public interface MemberMapper {
    //插入预约的会员信息
    Integer saveMember(Member member);

    //根据手机号查询
    Member findByPhone(String phone);

    //根据id查询详情
    Member findById(Integer phone);

    /**
     * 查询日期之前的用户数量
     * @param endDate
     * @return
     */
    Long countByRegTimeBefore(@Param("endDate") String endDate);
}
