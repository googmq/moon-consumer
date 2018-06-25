package cn.minqi.consumer.util;


import cn.minqi.consumer.model.RespBase;

import java.text.MessageFormat;

public enum CommonEnum {
    CODE_1000(Integer.valueOf(1000), "成功"),
    CODE_1001(Integer.valueOf(1001), "无权限访问"),
    CODE_1002(Integer.valueOf(1002), "信息不存在"),
    CODE_1003(Integer.valueOf(1003), "支付方式错误"),
    CODE_1004(Integer.valueOf(1004), "服务器处理异常"),
    CODE_1005(Integer.valueOf(1005), "支付请求MD5签名失败"),
    CODE_1006(Integer.valueOf(1006), "未查询到订单信息"),
    CODE_1007(Integer.valueOf(1007), "请求超时"),
    CODE_1008(Integer.valueOf(1008), "信息已存在"),
    CODE_1009(Integer.valueOf(1009), "登录状态已失效, 请重新登录"),
    CODE_1010(Integer.valueOf(1010), "验证失败"),
    CODE_1011(Integer.valueOf(1011), "MD5签名失败"),
    CODE_1012(Integer.valueOf(1012), "用户信息缓存失败"),
    CODE_1013(Integer.valueOf(1013), "登录失败次数超过限制"),
    CODE_1015(Integer.valueOf(1015), "验证码错误，请重新获取验证码"),
    CODE_1100(Integer.valueOf(1100), "MD5签名验证失败"),


    CODE_1016(Integer.valueOf(1016), "{0}"),
    CODE_2000(Integer.valueOf(2000), "网络异常,请稍后再试"),
    CODE_2001(Integer.valueOf(2001), "返回参数为空"),
    CODE_2002(Integer.valueOf(2002), "调用失败"),
    CODE_2003(Integer.valueOf(2003), "未查询到imei信息"),
    CODE_2004(Integer.valueOf(2004), "imei校验失败"),

    CODE_2005(Integer.valueOf(2005), "订购失败"),
    CODE_2006(Integer.valueOf(2006), "校验失败"),
    CODE_2007(Integer.valueOf(2007), "失败"),
    CODE_2008(Integer.valueOf(2008), "请求对象为空!"),//请求参数长度异常
    CODE_2009(Integer.valueOf(2009), "{0}不能为空"),//非空校验
    CODE_2010(Integer.valueOf(2010), "促销员审核提交失败"),
    CODE_2011(Integer.valueOf(2011), "促销员注册异常，请稍后再试"),
    CODE_2012(Integer.valueOf(2012), "关联渠道查询失败"),

    CODE_2013(Integer.valueOf(2013), "未查到数据"),
    CODE_2014(Integer.valueOf(2014), "营销活动订购校验失败"),
    CODE_2015(Integer.valueOf(2015), "营销活动订购失败"),
    CODE_2016(Integer.valueOf(2016), "请勿重复注册"),
    CODE_2017(Integer.valueOf(2017), "该手机号尚未注册"),
    CODE_2018(Integer.valueOf(2018), "待审核"),
    CODE_2019(Integer.valueOf(2019), "初审未通过"),

    CODE_5005(Integer.valueOf(5005), "营销活动重复订购"),

    CODE_2020(Integer.valueOf(2020), "导入手机号入库失败 {0}"),

    CODE_2021(Integer.valueOf(2021), "工作簿出现IO异常"),

    CODE_2022(Integer.valueOf(2022), "文件不合法"),
    CODE_2023(Integer.valueOf(2023), "{0}")
    ;

    


    private Integer code;
    private String value;

    private CommonEnum(Integer code, String value) {
        this.code = code;
        this.value = value;
    }

    public final Integer getCode() {
        return this.code;
    }

    public final String getValue() {
        return this.value;
    }
    public RespBase getRespBase(Object... params) {
        return new RespBase(code, MessageFormat.format(value, params));
    }
    public String toString() {
        return this.code + "=" + this.value;
    }

}
