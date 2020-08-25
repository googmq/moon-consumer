package cn.minqi.consumer.entity;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * <p>
 *
 * </p>
 *
 * @author minqi
 * @since 2018-06-12
 */
@Document(collection = "vip")
@AllArgsConstructor
@Setter
@Getter
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @Id
    private ObjectId id;
    /**
     * 用户名字
     */
    private String userName;
    /**
     * 用户手机号
     */
    private String userPhone;
    /**
     * 用户电子邮件
     */
    private String userEmail;
    /**
     * 用户密码
     */
    private String userPassword;
    /**
     * 身份证号
     */
    private String certificatesNumber;
    /**
     * 银行卡号
     */
    private String accountNumber;
    /**
     * 微信号
     */
    private String wechat;
    /**
     * 用户头像
     */
    private String avatar;
    /**
     * 扩展字段1
     */
    private String ext1User;
    /**
     * 扩展字段二
     */
    private String ext2User;
    /**
     * 扩展字段三
     */
    private String ext3User;
    /**
     * 扩展字段四
     */
    private String ext4User;
    /**
     * 扩展字段五
     */
    private String ext5User;
    /**
     * 备注
     */
    private String remark;
    /**
     * 创建时间
     */
    private Long createdTime;
    /**
     * 更新时间
     */
    private Long updatedTime;
    /**
     * 创建人
     */
    private Long createdBy;
    /**
     * 修改人
     */
    private Long updatedBy;
    /**
     * 是否删除
     */
    private String deleted;
    /**
     * 省份名称
     */
    private String province;
    /**
     * 城市名称
     */
    private String city;
    /**
     * 地区名称
     */
    private String district;
    /**
     * 详情地址
     */
    private String detailAddress;
    /**
     * 省份
     */
    private String provinceCode;
    /**
     * 城市
     */
    private String cityCode;
    /**
     * 地区
     */
    private String districtCode;


}
