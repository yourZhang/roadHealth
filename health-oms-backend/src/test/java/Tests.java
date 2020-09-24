import cn.zys.config.SpringConfig;
import cn.zys.pojo.TestUser;
import cn.zys.service.UserService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @program: road-health
 * @description:
 * @author: xiaozhang6666
 * @create: 2020-09-21 16:24
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringConfig.class})
public class Tests {
    @Reference
    UserService userService;

    @Test
    public void tesst01(){
        List<TestUser> all = userService.findAll();
        System.out.println(all);
    }
}
