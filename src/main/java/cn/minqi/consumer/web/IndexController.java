package cn.minqi.consumer.web;

import cn.minqi.consumer.config.ServiceConfig;
import cn.minqi.consumer.util.StringEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author minqi
 */
@Controller
public class IndexController {

    @Autowired
    private ServiceConfig config;

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
        try {
            String decodeStr = StringEncoder.decodeByXOR(token, config.getBaseEncodeStr());
            String[] split = decodeStr.split(config.getSplitStr());
            if (config.getBaseEncodeStr().equals(split[0])) {
                return "chose";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "exception";
    }
}
