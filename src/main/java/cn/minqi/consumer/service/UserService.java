package cn.minqi.consumer.service;

import cn.minqi.consumer.config.ServiceConfig;
import cn.minqi.consumer.config.exception.ServiceException;
import cn.minqi.consumer.persistence.entity.User;
import cn.minqi.consumer.persistence.mapper.UserMapper;
import cn.minqi.consumer.util.MD5Util;
import cn.minqi.consumer.util.RedisClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.UUID;

/**
 * @author minqi
 */
@Service
@Slf4j
public class UserService {

    @Autowired
    private RedisClient redisClient;

    @Autowired
    private ServiceConfig config;

    @Autowired
    private UserMapper userMapper;

    public String login(String account, String password) throws ServiceException {
        if (!StringUtils.isEmpty(account)
                && !StringUtils.isEmpty(password)
                && config.getBaseEncodeStr().equals(account)) {
            String md5 = MD5Util.getMd5(password);
            if (config.getUser().equals(md5)) {
                String token = UUID.randomUUID().toString();
                redisClient.set(token, account, 2592000L);
                return token;
            }
        }
        throw new ServiceException(2333001, "登录失败，请检查账号，密码。");
    }

    public User findByUserId(Long id) {
        List<User> userList = userMapper.selectList(null);
        userList.forEach(System.out::println);
        return null;
    }

    @Transactional
    public void insertUser(User user) {
        userMapper.insert(user);
    }
}
