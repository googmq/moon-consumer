package cn.minqi.consumer.bo.base.page;

import cn.minqi.consumer.bo.base.JsonBase;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Map;
import javax.validation.constraints.Min;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Range;

/**
 * 分页请求
 *
 * @author minqi
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ReqPage extends JsonBase {

    @Range(min = 1, max = 100, message = "分页大小必须在1～100之间")
    private int limit;

    @Min(value = 0, message = "偏移量不能小于0")
    private int offset;

    private String order;

    private String sort;

    private String search;

    private Map<String, Object> searchMap;

    /**
     * 计算当前页
     *
     * @return 当前页码，从0开始
     */
    @JsonIgnore
    public int getPage() {
        if (offset <= 0) {
            return 0;
        }

        return offset / limit;
    }
}