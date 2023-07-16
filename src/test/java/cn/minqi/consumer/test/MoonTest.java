package cn.minqi.consumer.test;

import cn.minqi.consumer.Application;
import cn.minqi.consumer.persistence.entity.User;
//import cn.minqi.consumer.persistence.mapper.UserMapper;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class MoonTest {

//    @Autowired
//    private UserMapper userMapper;

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
//        List<User> userList = userMapper.selectList(null);
//        userList.forEach(System.out::println);
    }

}