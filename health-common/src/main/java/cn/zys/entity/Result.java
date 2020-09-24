package cn.zys.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author zhangmeng
 * @description
 * @date 2019/9/6
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result implements Serializable {
    private Boolean flag;//执行结果，true为执行成功 false为执行失败
    private String message;//返回结果信息
    private Object data;//返回数据

    public Result(boolean flag, String message) {
        this();
        this.flag = flag;
        this.message = message;
    }
}