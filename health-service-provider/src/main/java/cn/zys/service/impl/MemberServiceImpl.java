package cn.zys.service.impl;

import cn.zys.common.MessageConst;
import cn.zys.entity.Result;
import cn.zys.mapper.MemberMapper;
import cn.zys.pojo.Member;
import cn.zys.service.MemberService;
import com.alibaba.dubbo.config.annotation.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: road-health
 * @description:
 * @author: xiaozhang6666
 * @create: 2020-09-29 17:03
 **/
@Service
@Slf4j
public class MemberServiceImpl implements MemberService {

    @Autowired
    MemberMapper memberMapper;

    @Override
    @Transactional(transactionManager = "transactionManager", isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public Member saveMember(Member member) {
        //先根据手机号查询是否有此用户
        final Member byPhone = memberMapper.findByPhone(member.getPhoneNumber());
        if (null != byPhone) {
            return byPhone;
        } else {
            final Integer integer = memberMapper.saveMember(member);
        }
        log.info("::::::::::::::::::::::::::::::::::::::::::::{}", member);
        return memberMapper.findById(member.getId());
    }

    @Override
    public Result getMemberReport(List<String> dates) {
        List<Integer> resultLIst = new ArrayList<>();
        for (String month : dates) {
            String endDate = month+".31";
            Long count = memberMapper.countByRegTimeBefore(endDate);
            resultLIst.add(count.intValue());
        }
        Map<String,Object> map = new HashMap<>();
        map.put("months",dates);
        map.put("memberCount",resultLIst);
        return  new Result(true, MessageConst.GET_MEMBER_NUMBER_REPORT_SUCCESS,map);
    }
}
