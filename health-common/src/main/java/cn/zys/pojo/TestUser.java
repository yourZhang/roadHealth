package cn.zys.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @program: road-health
 * @description: 测试用户
 * @author: xiaozhang6666
 * @create: 2020-09-20 22:43
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestUser implements Serializable {
    private Integer id;
    private String username;
    private Date birthday;
    private String sex;
    private String address;
}
