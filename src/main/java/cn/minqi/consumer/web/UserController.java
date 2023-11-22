package cn.minqi.consumer.web;

import cn.minqi.consumer.bo.base.RespBase;
import cn.minqi.consumer.config.exception.ServiceException;
import cn.minqi.consumer.persistence.entity.User;
import cn.minqi.consumer.service.UserService;
import com.google.common.collect.Lists;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
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
    public RespBase<List<RecordDTO>> test() {
//        userService.findByUserId(null);
        Random random = new Random();
        int j = random.nextInt(3);
        List<RecordDTO> objects = Lists.newArrayList();
        for (int i = 0; i < j; i++) {
            RecordDTO recordDTO = new RecordDTO();
            recordDTO.setName("name" + i);
            recordDTO.setType(1);
            objects.add(recordDTO);
        }
        RecordDTO recordDTO = new RecordDTO();
        recordDTO.setName("name初始的" );
        recordDTO.setType(1);
        objects.add(recordDTO);
        return new RespBase<>(objects);
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
