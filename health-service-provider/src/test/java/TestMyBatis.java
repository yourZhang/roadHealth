import cn.zys.config.SpringConfig;
import cn.zys.pojo.TestUser;
import cn.zys.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

/**
 * @program: road-health
 * @description:
 * @author: xiaozhang6666
 * @create: 2020-09-20 22:38
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringConfig.class})
@Slf4j
public class TestMyBatis {
    @Autowired
    UserService userService;

    @Test
    public void test01(){
        List<TestUser> all = userService.findAll();
        System.out.println("=====================================================================");
        log.info("######{}",all);
        System.out.println(all);
        System.out.println("=====================================================================");
    }

    @Test
    public void test02(){
        Integer integer = userService.saveUser(new TestUser(null, "大计基", new Date(), "男", "火星人"));
        System.out.println(integer);
    }
}
