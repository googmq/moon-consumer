package cn.minqi.consumer.bo.base.page;

import cn.minqi.consumer.bo.base.JsonBase;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel("分页请求")
public class ReqPage extends JsonBase {

    @Range(min = 1, max = 100, message = "分页大小必须在1～100之间")
    @ApiModelProperty("分页大小")
    private int limit;

    @Min(value = 0, message = "偏移量不能小于0")
    @ApiModelProperty("当前偏移量")
    private int offset;

    @ApiModelProperty("顺序：desc、asc")
    private String order;

    @ApiModelProperty("排序属性，逗号分割，支持多个")
    private String sort;

    @ApiModelProperty("随机查询条件")
    private String search;

    @ApiModelProperty("精确查询，键为字段名，值为查询字符串")
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