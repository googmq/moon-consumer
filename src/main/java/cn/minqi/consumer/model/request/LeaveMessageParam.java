package cn.minqi.consumer.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LeaveMessageParam {

    private String phone;
    private String email;
    private String message;
}
