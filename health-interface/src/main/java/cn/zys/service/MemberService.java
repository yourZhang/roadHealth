package cn.zys.service;

import cn.zys.entity.Result;
import cn.zys.pojo.Member;

import java.util.List;

public interface MemberService {
    //插入预约的会员信息
    Member saveMember(Member member);

    //图标渲染
    Result getMemberReport(List<String> dates);
}
