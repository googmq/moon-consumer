package cn.minqi.consumer.service.impl;

import cn.minqi.consumer.entity.Goods;
import cn.minqi.consumer.mapper.GoodsMapper;
import cn.minqi.consumer.model.BaseResponse;
import cn.minqi.consumer.model.request.GoodsPageParam;
import cn.minqi.consumer.service.IGoodsService;
import cn.minqi.consumer.util.BackResponseUtil;
import cn.minqi.consumer.util.ResponseConvert;
import cn.minqi.consumer.util.ReturnCodeEnum;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.plugins.Page;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author minqi
 * @since 2018-06-06
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements IGoodsService {
    public GoodsServiceImpl() {
        super();
     }

    public GoodsServiceImpl(GoodsMapper baseMapper) {
        this.baseMapper = baseMapper;
     }
    /**
    * 添加方法
    * @return
    */
    @Override
    public BaseResponse add(Goods model) {
        boolean back = this.insert(model);
        BaseResponse baseResponse = ResponseConvert.convert(back);
        return baseResponse;
        }
    /**
    * 删除
    * @return
    */
    @Override
    public BaseResponse delete(Goods model) {
        BaseResponse baseResponse ;
        if (null == model || null == model.getId()) {
        baseResponse = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1006.getCode());
        } else {
        boolean back = this.deleteById(model.getId());
        baseResponse = ResponseConvert.convert(back);
        }
        return baseResponse;
        }
    /**
    * 修改方法
    * @return
    */
    @Override
    public BaseResponse update(Goods model) {
        BaseResponse baseResponse ;
        if (null == model || null == model.getId()) {
        baseResponse = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1006.getCode());
        } else {
        boolean back = this.updateById(model);
        baseResponse = ResponseConvert.convert(back);
        }
        return baseResponse;
        }
    /**
    * 单个查询
    * @return
    */
    @Override
    public BaseResponse query(Goods model) {
        BaseResponse baseResponse;
        if (null == model || null == model.getId()) {
        baseResponse = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1006.getCode());
        } else {
        Goods data = this.selectById(model.getId());
        if (null != data) {
        baseResponse = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1000.getCode());
        baseResponse.setDataInfo(data);
        } else {
        baseResponse = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1002.getCode());
            }
        }
        return baseResponse;
        }

    /**
    * 条件查询分页列表
    * @param pageParam
    * @return
    */
    @Override
    public Page page(GoodsPageParam pageParam) {
        Page<Goods> page = new Page<Goods>();

        Page<Goods> pageResponse = null;
        //封装条件
        EntityWrapper<Goods> ew = new EntityWrapper<Goods>();

        //针对分页判断
        if (null != pageParam && null != pageParam.getPageNumber() && null != pageParam.getPageSize()) {
            page.setCurrent(pageParam.getPageNumber());
            page.setSize(pageParam.getPageSize());
            pageResponse = this.selectPage(page, ew);
        } else {
            List<Goods> selectList = this.selectList(ew);
            pageResponse = new Page<>();
            pageResponse.setRecords(selectList);
        }
        //记录数
        pageResponse.setTotal(this.selectCount(ew));
        return pageResponse;
        }
}
