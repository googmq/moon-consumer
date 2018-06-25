package cn.minqi.consumer.service;

import cn.minqi.consumer.entity.User;
import cn.minqi.consumer.model.BaseResponse;
import cn.minqi.consumer.model.request.UserPageParam;
import com.baomidou.mybatisplus.service.IService;
import com.baomidou.mybatisplus.plugins.Page;
/**
 * <p>
 *  服务类
 * </p>
 *
 * @author minqi
 * @since 2018-06-12
 */
public interface IUserService extends IService<User> {
    public BaseResponse add(User model);

    public BaseResponse delete(User model);

    public BaseResponse update(User model);

    public BaseResponse query(User model);

    public Page page(UserPageParam pageParam);

    public BaseResponse login(User model);

    public BaseResponse register(User model);
}
