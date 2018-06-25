package cn.minqi.consumer.model;

import cn.minqi.consumer.util.CommonEnum;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
public class RespBase<T> extends BaseResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 返回代码，00000表示成功，其他请查看错误描述
     */
    public static   RespBase SYSTEMERROR = new RespBase(9999, "系统异常");

    /**
     * 返回描述
     */

    private String tid = UUID.randomUUID().toString();


    /**
     * 返回结果
     */
    protected T result;

    //构造函数
    public RespBase(T result) {
        this.result = result;
    }

    // 构造函数
    public RespBase() {
    }
    //枚举
    public RespBase(CommonEnum returnCodeEnum){
        this.returnCode =  returnCodeEnum.getCode();
        this.message = returnCodeEnum.getValue();
    }
    // 构造函数
    public RespBase(Integer code, String desc) {
        this.returnCode = code;
        this.message = desc;

    }
}
