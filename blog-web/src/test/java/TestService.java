
import com.jt.blog.utils.SpringContextUtil;
import org.aspectj.lang.annotation.Aspect;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * @author : 戴瑞
 * @create 2016-08-30 12
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestService {

    @Test
    public void test1(){
        ApplicationContext context = SpringContextUtil.getApplicationContext();
        String[] beanNames = context.getBeanNamesForAnnotation(Aspect.class);
        for(String name : beanNames){
            System.out.println(name);
        }
    }
}
