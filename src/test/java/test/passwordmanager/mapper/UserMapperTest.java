package test.passwordmanager.mapper;

import com.d.passwordmanager.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

/**
 * @author: Ding
 * @date: 2022/8/22 7:49
 * @description:
 * @modify:
 */


// 上面两个注解可以替换成一个
@SpringJUnitConfig(locations = {"classpath:application-context.xml"})
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelectIdByPassword() {
        System.out.println(userMapper.selectIdByPassword("123"));
    }
}
