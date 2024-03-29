package cn.minqi.consumer.bo.base;

import cn.minqi.consumer.util.JacksonUtil;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.PropertyNamingStrategy.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

/**
 * 定义了通过Jackson序列化时的基本规则，所有JSON对象都应继承至该基础类.
 *
 * @author minqi
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
@JsonNaming(SnakeCaseStrategy.class)
public abstract class JsonBase {

    @Override
    public String toString() {
        try {
            return JacksonUtil.bean2Json(this);
        } catch (Exception e) {
            return super.toString();
        }
    }

}
