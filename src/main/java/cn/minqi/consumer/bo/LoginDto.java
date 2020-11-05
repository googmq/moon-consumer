package cn.minqi.consumer.bo;

import cn.minqi.consumer.bo.base.JsonBase;
import lombok.Data;

/**
 * @author MinQi create_time 2019-07-05 09:01
 **/

@Data
public class LoginDto extends JsonBase {

    private String account;

    private String password;
}
