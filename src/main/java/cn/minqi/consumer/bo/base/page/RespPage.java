package cn.minqi.consumer.bo.base.page;

import cn.minqi.consumer.bo.base.JsonBase;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 分页结果
 *
 * @param <T> 行数据类型
 * @author minqi
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("分页结果")
public class RespPage<T> extends JsonBase {

    @ApiModelProperty("总条目数")
    private long total;
    @ApiModelProperty("总分页数")
    private long totalPage;
    @ApiModelProperty("当前页码，从0开始")
    private int page;
    @ApiModelProperty("当前分页结果条目数")
    private int size;
    @ApiModelProperty("分页结果集")
    private List<T> rows;
}
