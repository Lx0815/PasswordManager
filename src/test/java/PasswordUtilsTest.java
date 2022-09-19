import com.d.passwordmanager.command.utils.PasswordUtils;
import org.junit.jupiter.api.Test;

/**
 * @author: Ding
 * @date: 2022/9/19 20:42
 * @description:
 * @modify:
 */


public class PasswordUtilsTest {

    @Test
    public void testEncode() {
        String encode = PasswordUtils.encode("123");
        System.out.println(PasswordUtils.strengthEncryption("123").equals(PasswordUtils.strengthEncryption("123")));
        System.out.println();
        System.out.println(encode);
        System.out.println(PasswordUtils.decode(encode));
    }

}
