package cn.minqi.consumer.bo;

import cn.minqi.consumer.bo.base.JsonBase;
import lombok.Data;

/**
 * @author minqi
 */
@Data
public class UserVo extends JsonBase {

    /**
     * 用户id
     */
    private Long id;

    /**
     * 用户名字
     */
    private String name;

    /**
     * 用户账号
     */
    private String account;

}
