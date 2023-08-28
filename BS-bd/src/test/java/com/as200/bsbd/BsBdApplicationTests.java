package com.as200.bsbd;

import com.as200.bsbd.sys.entity.User;
import com.as200.bsbd.sys.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class BsBdApplicationTests {
    @Resource
    private UserMapper userMapper;


    @Test
    void testMapper() {
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);

    }

}
