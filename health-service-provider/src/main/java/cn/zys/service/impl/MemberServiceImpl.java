package cn.zys.service.impl;

import cn.zys.mapper.MemberMapper;
import cn.zys.pojo.Member;
import cn.zys.service.MemberService;
import com.alibaba.dubbo.config.annotation.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
}
