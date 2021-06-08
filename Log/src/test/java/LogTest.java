
import com.spring5.test.User;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.lang.Nullable;

/**
 * @title: LogTest
 * @Author Wen
 * @Date: 2021/6/7 13:47
 * @Version 1.0
 */
public class LogTest {

    @Nullable
    private String name;

    private final Logger log = LoggerFactory.getLogger(LogTest.class);

    @Test
    public void test01() {

        log.info("hello log4j2");
        log.warn("hello log4j2");

    }

    /**
     * 函数式风格创建对象,交给Spring进行管理
     */
    @Test
    public void testGenericApplicationContext() {

        //1. 创建GenericApplicationContext对象
        GenericApplicationContext context = new GenericApplicationContext();
        context.refresh();
        //2. 调用context的方法对象注册
        context.registerBean(User.class, () -> new User());

        //以注解的方式或者xml配置方式,默认的bean 为全类名首字母小写,但这里是不可取的,会直接报异常,找不到对应的bean
        //org.springframework.beans.factory.NoSuchBeanDefinitionException: No bean named 'user' available
        //User user = (User)context.getBean("user");
        //3. 获取在Spring注册的对象
        User user = (User) context.getBean("com.spring5.test.User");
        System.out.println(user);
    }

    @Test
    public void testGenericApplicationContext2() {

        //1. 创建GenericApplicationContext对象
        GenericApplicationContext context = new GenericApplicationContext();
        context.refresh();
        //2. 调用context的方法对象注册
        context.registerBean("user1", User.class, () -> new User());

        //3. 获取在Spring注册的对象
        User user = (User) context.getBean("user1");
        System.out.println(user);
    }

}