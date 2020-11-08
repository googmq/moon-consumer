package cn.minqi.consumer.bo.base.page;

import cn.minqi.consumer.bo.base.JsonBase;
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
public class RespPage<T> extends JsonBase {

    private long total;
    private long totalPage;
    private int page;
    private int size;
    private List<T> rows;
}
