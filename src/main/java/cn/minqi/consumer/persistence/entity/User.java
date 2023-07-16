package cn.minqi.consumer.persistence.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("t_user")
public class User {
    private Long id;
    private String userName;
    private Integer age;
    private String email;
}
