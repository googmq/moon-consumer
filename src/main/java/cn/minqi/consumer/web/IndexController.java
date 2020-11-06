package cn.minqi.consumer.web;

import cn.minqi.consumer.util.RedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author minqi
 */
@Controller
public class IndexController {

    @Autowired
    private RedisClient redisClient;

    /**
     * 自定义登录界面
     *
     * @return 登录界面
     */
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    /**
     * exception界面
     *
     * @return exception界面
     */
    @GetMapping("/exception")
    public String exception() {
        return "exception";
    }

    /**
     * chose界面
     *
     * @return chose界面
     */
    @GetMapping("/chose")
    public String chose(@RequestParam String token) {
        String str = redisClient.get(token);
        if (StringUtils.isEmpty(str)) {
            return "exception";
        }
        return "chose";
    }
}
