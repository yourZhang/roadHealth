package cn.zys.mapper;

import cn.zys.pojo.Member;

public interface MemberMapper {
    //插入预约的会员信息
    Integer saveMember(Member member);

    //根据手机号查询
    Member findByPhone(String phone);

    //根据id查询详情
    Member findById(Integer phone);
}
