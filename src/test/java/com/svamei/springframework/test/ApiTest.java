package com.svamei.springframework.test;

import com.sun.applet2.preloader.event.ApplicationExitEvent;
import com.svamei.springframework.aop.AdvisedSupport;
import com.svamei.springframework.aop.TargetSource;
import com.svamei.springframework.aop.aspectj.AspectJExpressionPointcut;
import com.svamei.springframework.aop.framework.Cglib2AopProxy;
import com.svamei.springframework.aop.framework.JdkDynamicAopProxy;
import com.svamei.springframework.beans.PropertyValue;
import com.svamei.springframework.beans.PropertyValues;
import com.svamei.springframework.beans.factory.BeanFactory;
import com.svamei.springframework.beans.factory.config.BeanDefinition;
import com.svamei.springframework.beans.factory.config.BeanReference;
import com.svamei.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.svamei.springframework.beans.factory.support.SimpleInstantiationStrategy;
import com.svamei.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import com.svamei.springframework.context.ApplicationListener;
import com.svamei.springframework.context.event.ContextClosedEvent;
import com.svamei.springframework.context.spport.ClassPathXmlApplicationContext;
import com.svamei.springframework.test.bean.*;

import com.svamei.springframework.test.event.CustomEvent;
import com.svamei.springframework.test.interceptor.UserServiceInterceptor;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;
import org.junit.Test;

import java.lang.reflect.Method;

/**
 * @ClassName ApiTest
 * @Description
 * @Author Svamei
 * @Author Svamei
 * @Date 9:07 2023/3/1
 **/
public class ApiTest {

    @Test
    public void test_BeanFactory() {

        // 初始化 BeanFactory与BeanDefinition
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        BeanDefinition userServiceBeanDefinition = new BeanDefinition(UserService.class);

        // 注册Bean
        beanFactory.registerBeanDefinition("userService", userServiceBeanDefinition);

        //实例化策略使用JDK反射
        beanFactory.setInstantiationStrategy(new SimpleInstantiationStrategy());

        // 获取Bean
        UserService userService = (UserService) beanFactory.getBean("userService", "Svamei");
        //UserService userService = (UserService) beanFactory.getBean("userService");
        System.out.println(userService);
        userService.queryUserInfo();

        // 获取Bean
        UserService userService2 = (UserService) beanFactory.getBean("userService");
        System.out.println(userService2);
    }

    //测试属性注入
    @Test
    public void test_BeanFactoryWithDI() {
        // 初始化 BeanFactory与BeanDefinition
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();


        // UserDao 注册
        beanFactory.registerBeanDefinition("userDao", new BeanDefinition(UserDao.class));

        // 3. UserService 设置属性[uId、userDao]
        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("uId", "10001"));
        propertyValues.addPropertyValue(new PropertyValue("userDao",new BeanReference("userDao")));

        BeanDefinition userServiceBeanDefinition = new BeanDefinition(UserService.class, propertyValues);

        // 注册Bean
        beanFactory.registerBeanDefinition("userService", userServiceBeanDefinition);

        //实例化策略使用JDK反射
        beanFactory.setInstantiationStrategy(new SimpleInstantiationStrategy());

        // 获取Bean
        UserService userService = (UserService) beanFactory.getBean("userService", "Svamei");
        //UserService userService = (UserService) beanFactory.getBean("userService");
        System.out.println(userService);
        userService.queryUserInfo();
    }

    @Test
    public void testXmlApplicationContext() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        applicationContext.registerShutdownHook();
        // 2. 获取Bean对象调用方法
        UserService userService = applicationContext.getBean("userService", UserService.class);
        userService.queryUserInfo();
    }

    @Test
    public void testAware() {
        // 1.初始化 BeanFactory
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        applicationContext.registerShutdownHook();

        // 2. 获取Bean对象调用方法
        UserService userService = applicationContext.getBean("userService", UserService.class);
        userService.queryUserInfo();
        System.out.println("ApplicationContextAware："+userService.getApplicationContext());
        System.out.println("BeanFactoryAware："+userService.getBeanFactory());
    }

    @Test
    public void testPrototype() {
        // 1.初始化 BeanFactory
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        applicationContext.registerShutdownHook();

        // 2. 获取Bean对象调用方法
        UserService userService01 = applicationContext.getBean("userService", UserService.class);
        UserService userService02 = applicationContext.getBean("userService", UserService.class);

        // 3. 配置 scope="prototype/singleton"
        System.out.println(userService01.hashCode());
        System.out.println(userService02.hashCode());

        userService01.queryUserInfo();

//        // 4. 打印十六进制哈希
//        System.out.println(userService01 + " 十六进制哈希：" + Integer.toHexString(userService01.hashCode()));
//        System.out.println(ClassLayout.parseInstance(userService01).toPrintable());

    }

    @Test
    public void testEnhance() throws Exception {
//        Enhancer enhancer = new Enhancer();
//        enhancer.setSuperclass(ProxyBeanFactory.class);
//        enhancer.setCallback(new NoOp() {
//            @Override
//            public int hashCode() {
//                return super.hashCode();
//            }
//        });
//        Object o = enhancer.create();

        ProxyBeanFactory proxyBeanFactory = new ProxyBeanFactory();
        UserDao object = proxyBeanFactory.getObject();
        System.out.println(object.queryUserName("323"));
    }

    @Test
    public void testReflect() {
        Class<ApplicationListener> applicationListenerClass = ApplicationListener.class;
        applicationListenerClass.getGenericInterfaces();
    }

    @Test
    public void testEvent() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        applicationContext.publishEvent(new CustomEvent(applicationContext, 1019129009086763L, "成功了！"));

        applicationContext.registerShutdownHook();
    }

    @Test
    public void testAop() throws NoSuchMethodException {
//        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut("execution(* com.svamei.springframework.test.bean.UserService.*(..))");
//        Class<UserService> clazz = UserService.class;
//        Method method = clazz.getDeclaredMethod("queryUserInfo");
//
//        System.out.println(pointcut.matches(clazz));
//        System.out.println(pointcut.matches(method, clazz));

        UserService userService = new UserService();

        AdvisedSupport advised1 = new AdvisedSupport();
        advised1.setTargetSource(new TargetSource(userService));
        advised1.setInterceptor(new UserServiceInterceptor());
        advised1.setMethodMatcher(new AspectJExpressionPointcut("execution(* *.queryUserInfo(..))"));

//        IUserService proxy_jdk  = (IUserService) new JdkDynamicAopProxy(advised).getProxy();
//        proxy_jdk.queryUserInfo();
        System.out.println(">>>>>>>>>>>");
        //System.out.println(proxy_jdk.register("kkkkkkjj"));

        IUserService proxy_cglib = (IUserService) new Cglib2AopProxy(advised1).getProxy();
        // 测试调用
        proxy_cglib.queryUserInfo();
    }

    @Test
    public void test_aop() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");

        IUserService userService = (IUserService) applicationContext.getBean("userService");
        userService.queryUserInfo();
    }

    @Test
    public void testXML() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        UserService userService = applicationContext.getBean("userService", UserService.class);
        userService.queryUserInfo();
    }

    @Test
    public void testDI() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        UserService userService = applicationContext.getBean("userService", UserService.class);
        userService.queryUserInfo();
    }

    @Test
    public void testDepend() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        Wife wife = applicationContext.getBean("wife", Wife.class);
        wife.queryHusband();
    }


}