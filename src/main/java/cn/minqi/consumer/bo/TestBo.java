package cn.minqi.consumer.bo;

import java.io.Serializable;
import lombok.Data;

/**
 * @author MinQi create_time 2019-07-05 09:01
 **/

@Data
public class TestBo implements Serializable {

    private Double time;

    private String value;
}
