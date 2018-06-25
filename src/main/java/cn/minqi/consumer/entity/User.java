package cn.minqi.consumer.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author minqi
 * @since 2018-06-12
 */
@Getter
@Setter
public class User extends Model<User> {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 用户名字
     */
	@TableField("user_name")
	private String userName;
    /**
     * 用户手机号
     */
	@TableField("user_phone")
	private String userPhone;
    /**
     * 用户电子邮件
     */
	@TableField("user_email")
	private String userEmail;
    /**
     * 用户密码
     */
	@TableField("user_password")
	private String userPassword;
    /**
     * 身份证号
     */
	@TableField("certificates_number")
	private String certificatesNumber;
    /**
     * 银行卡号
     */
	@TableField("account_number")
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
	@TableField("ext1_user")
	private String ext1User;
    /**
     * 扩展字段二
     */
	@TableField("ext2_user")
	private String ext2User;
    /**
     * 扩展字段三
     */
	@TableField("ext3_user")
	private String ext3User;
    /**
     * 扩展字段四
     */
	@TableField("ext4_user")
	private String ext4User;
    /**
     * 扩展字段五
     */
	@TableField("ext5_user")
	private String ext5User;
    /**
     * 备注
     */
	private String remark;
    /**
     * 创建时间
     */
	@TableField("created_time")
	private Long createdTime;
    /**
     * 更新时间
     */
	@TableField("updated_time")
	private Long updatedTime;
    /**
     * 创建人
     */
	@TableField("created_by")
	private Long createdBy;
    /**
     * 修改人
     */
	@TableField("updated_by")
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
	@TableField("detail_address")
	private String detailAddress;
    /**
     * 省份
     */
	@TableField("province_code")
	private String provinceCode;
    /**
     * 城市
     */
	@TableField("city_code")
	private String cityCode;
    /**
     * 地区
     */
	@TableField("district_code")
	private String districtCode;

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
