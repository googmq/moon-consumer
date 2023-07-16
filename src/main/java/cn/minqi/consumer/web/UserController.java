package cn.minqi.consumer.web;

import cn.minqi.consumer.bo.base.RespBase;
import cn.minqi.consumer.config.exception.ServiceException;
import cn.minqi.consumer.persistence.entity.User;
import cn.minqi.consumer.service.UserService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

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

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public RespBase<RecordDTO> test() {
//        userService.findByUserId(null);
        RecordDTO recordDTO = new RecordDTO();
        Random random = new Random();
        recordDTO.setName("name" + random.nextInt());
        recordDTO.setType(1);
        return new RespBase<>(recordDTO);
    }

    @Data
    class RecordDTO {
        private String name;
        private int type;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public RespBase<String> add() {
        for (int i = 1; i <= 10; i++) {
            User user = new User();
            user.setUserName("test" + i);
            user.setAge(i);
            user.setEmail("test_email");
            userService.insertUser(user);
        }
        return new RespBase<>("ok");
    }
}
