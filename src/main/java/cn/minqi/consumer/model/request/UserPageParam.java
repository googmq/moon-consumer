package cn.minqi.consumer.model.request;

import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 *  分页查询请求包装类
 * </p>
 *
 * @author minqi
 * @since 2018-06-12
 */
@Getter
@Setter
public class UserPageParam{
    private Integer pageNumber;
    private Integer pageSize;
    private Integer startRow;
}
