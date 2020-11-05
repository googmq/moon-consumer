package cn.minqi.consumer.bo.base;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.util.StringUtils;

/**
 * 所有操作的响应结果类.
 *
 * @param <T> 数据结果对象范型
 * @author minqi
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("响应结果")
public class RespBase<T> extends JsonBase {

    /**
     * 成功的标记
     */
    public static final int SUCCESS = 0;
    /**
     * 未知错误，代表运行时异常
     */
    public static final int ERROR = -1;

    /**
     * 默认的成功响应结果
     */
    public static final RespBase<Void> OK_RESP_BASE = new RespBase<>();

    @ApiModelProperty("错误代码")
    private int code;

    @ApiModelProperty("错误消息")
    private String msg;

    @ApiModelProperty("响应结果数据对象")
    private T data;

    public RespBase() {
        this.code = SUCCESS;
        this.msg = "";
    }

    public RespBase(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public RespBase(Exception e) {
        this();
        this.code = -1;
        this.msg = StringUtils.isEmpty(e.getMessage()) ? "error" : e.getMessage();
    }

    public RespBase(T data) {
        this();
        this.data = data;
    }


    /**
     * 输出错误字符串
     *
     * @return 错误字符串
     */
    public String toErrorString() {
        if (this.code == SUCCESS) {
            return "success";
        }

        return String.format("[%d]%s", this.code, this.msg);
    }
}