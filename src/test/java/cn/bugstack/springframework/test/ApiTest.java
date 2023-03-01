package cn.bugstack.springframework.test;

import cn.bugstack.springframework.test.bean.UserService;
import com.kxs.springframework.BeanDefinition;
import com.kxs.springframework.BeanFactory;
import org.junit.Test;

/**
 * @ClassName ApiTest
 * @Description
 * @Author Svamei
 * @Date 9:07 2023/3/1
 **/
public class ApiTest {

    @Test
    public void test_BeanFactory() {

        // 初始化 BeanFactory与BeanDefinition
        BeanFactory beanFactory = new BeanFactory();
        BeanDefinition userServiceBeanDefinition = new BeanDefinition(new UserService());

        // 注册Bean
        beanFactory.registerBeanDefinition("userService", userServiceBeanDefinition);

        // 获取Bean
        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.queryUserInfo();
    }
}