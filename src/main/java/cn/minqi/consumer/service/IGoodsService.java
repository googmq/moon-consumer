package cn.minqi.consumer.service;

import cn.minqi.consumer.entity.Goods;
import cn.minqi.consumer.model.BaseResponse;
import cn.minqi.consumer.model.request.GoodsPageParam;
import com.baomidou.mybatisplus.service.IService;
import com.baomidou.mybatisplus.plugins.Page;
/**
 * <p>
 *  服务类
 * </p>
 *
 * @author minqi
 * @since 2018-06-06
 */
public interface IGoodsService extends IService<Goods> {
    public BaseResponse add(Goods model);

    public BaseResponse delete(Goods model);

    public BaseResponse update(Goods model);

    public BaseResponse query(Goods model);

    public Page page(GoodsPageParam pageParam);
}
