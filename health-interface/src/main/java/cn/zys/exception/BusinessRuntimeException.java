package cn.zys.exception;

/**
 * @author ：zhang
 * @date ：Created in 2019/11/29
 * @description ：业务运行时异常
 * @version: 1.0
 */
public class BusinessRuntimeException extends RuntimeException {

    public BusinessRuntimeException(String message) {
        super(message);
    }

    public BusinessRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }
}
