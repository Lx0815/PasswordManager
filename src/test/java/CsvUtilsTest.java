import com.d.passwordmanager.command.utils.CsvUtils;
import com.d.passwordmanager.pojo.PasswordRecord;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

/**
 * @author: Ding
 * @date: 2022/9/20 16:23
 * @description:
 * @modify:
 */


public class CsvUtilsTest {

    @Test
    public void testReadAll() throws FileNotFoundException {
        List<PasswordRecord> recordList = CsvUtils.readAll(
                PasswordRecord.class,
                Map.of("name", "description",
                        "url", "domainName",
                        "username", "username",
                        "password", "password"),
                Paths.get("C:\\Users\\15074\\Desktop\\more\\Microsoft Edge 密码.csv"));
        System.out.println(recordList);
    }

}
