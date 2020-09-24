package cn.zys.pojoVo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @program: road-health
 * @description: VoUser
 * @author: xiaozhang6666
 * @create: 2020-09-21 20:28
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVo implements Serializable {
    private String username;
    private String password;
}
