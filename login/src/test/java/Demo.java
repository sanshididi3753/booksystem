import com.nmsl.feign.client.MailClient;
import com.nmsl.feign.pojo.Mail;
import com.nmsl.login.LoginApplication;
import com.nmsl.login.mapper.AdminMapper;
import com.nmsl.login.mapper.UserMapper;
import com.nmsl.login.pojo.Admin;
import com.nmsl.login.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = LoginApplication.class)
public class Demo {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MailClient mailClient;

    @Autowired
    private AdminMapper adminMapper;

    @Test
    public void test2(){
        User user = new User();
        user.setUserEmail("123");
        List<User> userList = userMapper.selectUserByEmail(user);
        System.out.println(userList.size());
        System.out.println(userList.toString());
    }

    @Test
    public void test3(){
        Mail mail = new Mail();
        mail.setEmail("2248511201@qq.com");
        mail.setConfirmCode("1527317709178671104");
        mailClient.sendMail(mail);
    }

    @Test
    public void test4(){
        List<Admin> admin = adminMapper.selectAdminByName("admin");
        System.out.println(admin.get(0).toString());
    }
}
