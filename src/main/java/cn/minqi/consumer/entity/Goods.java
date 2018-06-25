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
 * @since 2018-06-06
 */
@Getter
@Setter
public class Goods extends Model<Goods> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
	@TableField("goods_name")
	private String goodsName;
	@TableField("shop_name")
	private String shopName;
	@TableField("goods_price")
	private Double goodsPrice;
	private String count;
	private String url;
	private String time;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
