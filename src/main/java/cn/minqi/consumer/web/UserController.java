package cn.minqi.consumer.web;

import cn.minqi.consumer.bo.base.RespBase;
import cn.minqi.consumer.config.exception.ServiceException;
import cn.minqi.consumer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author minqi
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public RespBase<String> login(@RequestParam String account,
        @RequestParam String password) throws ServiceException {
        return new RespBase<>(userService.login(account, password));
    }
}
