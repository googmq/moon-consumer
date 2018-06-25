package cn.minqi.consumer.service.impl;

import cn.minqi.consumer.entity.User;
import cn.minqi.consumer.mapper.UserMapper;
import cn.minqi.consumer.model.BaseResponse;
import cn.minqi.consumer.model.request.UserPageParam;
import cn.minqi.consumer.service.IUserService;
import cn.minqi.consumer.util.BackResponseUtil;
import cn.minqi.consumer.util.ResponseConvert;
import cn.minqi.consumer.util.ReturnCodeEnum;
import cn.minqi.consumer.util.StringUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author minqi
 * @since 2018-06-12
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    public UserServiceImpl() {
        super();
     }

    public UserServiceImpl(UserMapper baseMapper) {
        this.baseMapper = baseMapper;
     }
    /**
    * 添加方法
    * @return
    */
    @Override
    public BaseResponse add(User model) {
        boolean back = this.insert(model);
        BaseResponse baseResponse = ResponseConvert.convert(back);
        return baseResponse;
        }
    /**
    * 删除
    * @return
    */
    @Override
    public BaseResponse delete(User model) {
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
    public BaseResponse update(User model) {
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
    public BaseResponse query(User model) {
        BaseResponse baseResponse;
        if (null == model || null == model.getId()) {
        baseResponse = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1006.getCode());
        } else {
        User data = this.selectById(model.getId());
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
    public Page page(UserPageParam pageParam) {
        Page<User> page = new Page<User>();

        Page<User> pageResponse = null;
        //封装条件
        EntityWrapper<User> ew = new EntityWrapper<User>();

        //针对分页判断
        if (null != pageParam && null != pageParam.getPageNumber() && null != pageParam.getPageSize()) {
        page.setCurrent(pageParam.getPageNumber());
        page.setSize(pageParam.getPageSize());
        pageResponse = this.selectPage(page, ew);
        } else {
        List<User> selectList = this.selectList(ew);
        pageResponse = new Page<>();
        pageResponse.setRecords(selectList);
        }
        //记录数
        pageResponse.setTotal(this.selectCount(ew));
        return pageResponse;
        }

    @Override
    public BaseResponse login(User model) {
        BaseResponse baseResponse = new BaseResponse();
        if (model == null || !StringUtil.isMobilePhone(model.getUserPhone())) {
            baseResponse = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1006.getCode());
        } else {
            EntityWrapper<User> ew = new EntityWrapper<User>();
            ew.eq("user_phone", model.getUserPhone());
            log.info("loginByPhone selectList params {}", JSONObject.toJSONString(ew));
            List<User> userList = this.selectList(ew);
            log.info("loginByPhone selectList result {}", JSONObject.toJSONString(userList));
            if (CollectionUtils.isNotEmpty(userList)) {
                baseResponse = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1000.getCode());
                User userInfo = userList.get(0);
                //判断密码是否正确
                if (StringUtil.isNotEmpty(model.getUserPassword()) && !model.getUserPassword().equals(userInfo.getUserPassword())) {
                    //失效状态体提示
                    baseResponse.setReturnCode(ReturnCodeEnum.CODE_1005.getCode());
                    baseResponse.setMessage("message.login.password.error");
                } else if ("1".equals(userInfo.getDeleted())) {
                    //生效状态
                    baseResponse.setDataInfo(userInfo);
                }else if ("0".equals(userInfo.getDeleted())) {
                    //审核中
                    baseResponse.setReturnCode(ReturnCodeEnum.CODE_1005.getCode());
                    baseResponse.setMessage("message.login.account.pending.status");
                } else {
                    //失效状态体提示 状态为2 或其他
                    baseResponse.setReturnCode(ReturnCodeEnum.CODE_1005.getCode());
                    baseResponse.setMessage("message.login.account.invalid");
                }
            } else {
                baseResponse.setReturnCode(ReturnCodeEnum.CODE_1005.getCode());
                baseResponse.setMessage("message.login.fail");
            }
        }
        log.info("Login response is : {}", baseResponse);
        return baseResponse;
    }

    @Override
    public BaseResponse register(User model) {
        BaseResponse baseResponse = null;
        log.info("注册传入参数{}", model);
        if (model != null && !model.getUserPhone().equals("")) {
            long time = System.currentTimeMillis();
            model.setCreatedTime(time);
            model.setUpdatedTime(time);
            User one = new User();
            one.setUserPhone(model.getUserPhone());
            one = baseMapper.selectOne(one);
            if (null != one) {
                baseResponse = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1008.getCode());
                baseResponse.setMessage("message.user.has.register");
                baseResponse.setDataInfo(one);
                return baseResponse;
            } else {
                boolean back = this.insert(model);
                baseResponse = ResponseConvert.convert(back);
                model.setUserPassword(null);
                baseResponse.setDataInfo(model);
            }
        } else {
            baseResponse = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1006.getCode());
        }
        return baseResponse;
    }


}
