package test.passwordmanager.mapper;

import com.d.passwordmanager.mapper.PasswordMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;


@SpringJUnitConfig(locations = {"classpath:application-context.xml"})
class PasswordMapperTest {

    @Autowired
    private PasswordMapper passwordMapper;

    @Test
    void selectAll() {
        System.out.println(passwordMapper.selectByKeyword(null));
    }
}