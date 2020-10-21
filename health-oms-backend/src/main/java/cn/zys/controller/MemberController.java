package cn.zys.controller;

import cn.zys.entity.Result;
import cn.zys.service.MemberService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @program: road-health
 * @description:
 * @author: xiaozhang6666
 * @create: 2020-10-08 14:46
 **/
@RestController
@RequestMapping("report")
public class MemberController {

    @Reference
    MemberService memberService;

    @RequestMapping("getMemberReport")
    public Result getMemberReport() {
        List<String> dates = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, -12);
        DateFormat dateFormat = new SimpleDateFormat("yyyy.MM");
        for (int i = 0; i < 12; i++) {
            calendar.add(Calendar.MONTH, 1);
            dates.add(dateFormat.format(calendar.getTime()));
        }
        return memberService.getMemberReport(dates);
    }
}
