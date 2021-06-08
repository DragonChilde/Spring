import com.spring5.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

/**
 * @title: JTest5
 * @Author Wen
 * @Date: 2021/6/7 15:29
 * @Version 1.0
 */

/*@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:bean.xml")*/
/**
 * 这个复合注解可以替代上面两个
 */
@SpringJUnitConfig(locations = "classpath:bean.xml")
public class JTest5 {

    @Autowired
    private UserService userService;

    @Test
    public void test1() {
        userService.testAbnormal();
    }
}