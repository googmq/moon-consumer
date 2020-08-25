package cn.minqi.consumer.model;

import java.io.Serializable;
import javax.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 接口返回基类
 */
@MappedSuperclass
@Getter
@Setter
@ToString(callSuper = true)
public class BaseResponse<T> extends Response implements Serializable {

    /**
     * 操作数据信息(请求结果成功返回)
     */
    protected T dataInfo;

    /**
     * Default Constructor
     */
    public BaseResponse() {
        super();
    }
}
