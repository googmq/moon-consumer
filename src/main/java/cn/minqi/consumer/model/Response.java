package cn.minqi.consumer.model;

import lombok.Data;

import java.io.Serializable;

/**
 * Response
 */
@Data
public class Response implements Serializable {
    /**
     * 请求结果
     */
    protected Integer returnCode;
    /**
     * 错误信息
     */
    protected String message;
}
