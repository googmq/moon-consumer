package cn.minqi.consumer.config.exception;

import lombok.Setter;

/**
 * @author minqi
 */
public class ServiceException extends Exception {

    /**
     * 支持通过RespBase生成Exception消息
     */
    @Setter
    private String msg;
    @Setter
    private Integer code;

    public ServiceException() {
    }

    public ServiceException(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 获取错误代码
     *
     * @return 错误代码
     */
    public int getCode() {
        return code != null ? code : -1;
    }

    @Override
    public String getMessage() {
        return this.msg;
    }

    /**
     * 重写堆栈填充，不填充错误堆栈信息，提高性能
     *
     * @return 异常
     */
    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }

    @Override
    public String toString() {
        return String.format("[%d]%s", this.getCode(), this.getMessage());
    }
}
