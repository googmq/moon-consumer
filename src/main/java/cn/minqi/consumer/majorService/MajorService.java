package cn.minqi.consumer.majorService;

import cn.minqi.consumer.entity.Goods;
import cn.minqi.consumer.entity.User;
import cn.minqi.consumer.model.BaseResponse;
import cn.minqi.consumer.model.request.GoodsPageParam;
import cn.minqi.consumer.service.IGoodsService;
import cn.minqi.consumer.service.IUserService;
import cn.minqi.consumer.util.BackResponseUtil;
import cn.minqi.consumer.util.ReturnCodeEnum;
import com.baomidou.mybatisplus.plugins.Page;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
@NoArgsConstructor
public class MajorService {

    @Autowired
    private IGoodsService iGoodsService;
    @Autowired
    private IUserService iUserService;
    /**
     * 查一个
     * @param goods
     * @return
     */
    public BaseResponse majorQuery(Goods goods) {
        return iGoodsService.query(goods);
    }

    /**
     * 分页查
     * @param goods
     * @return
     */
    public Page majorPage(GoodsPageParam goods) {
        return iGoodsService.page(goods);
    }

    /**
     * 用户登录
     * @param user
     * @return
     */
    public BaseResponse userLogin(User user){

        return iUserService.login(user);
    }

    /**
     * 用户注册
     * @param user
     * @return
     */
    public BaseResponse register(User user){

        return iUserService.register(user);
    }
}
