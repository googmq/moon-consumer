package cn.minqi.consumer.util;

import java.util.regex.Pattern;

/**
 * 数据校验的工具类
 *
 * @author MinQi create_time 2019-03-21 08:47
 **/

public class CheckUtils {

    /**
     * 校验是不是手机号
     *
     * @param mobileNum 手机号
     * @return 是不是手机号
     */
    public static boolean isMobileNO(String mobileNum) {
        return Pattern.matches("^1\\d{10}$", mobileNum);
    }

}
