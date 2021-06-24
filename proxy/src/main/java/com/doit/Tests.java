package com.doit;

import com.doit.proxy.CglibProxy;
import com.doit.proxy.JdkProxy;
import com.doit.proxy.User;
import com.doit.service.UserService;
import com.doit.service.impl.UserServiceImpl;
import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Tests {

    @Test
    public void test01(){
//        User user = new User();
//        user.sayHello();

        UserService userService = new UserServiceImpl();
        JdkProxy jdkProxy = new JdkProxy(userService);
        UserService o = (UserService) Proxy.newProxyInstance
                (userService.getClass().getClassLoader(), userService.getClass().getInterfaces(), jdkProxy);
        o.sayHello();

        System.out.println("=====================================");

        UserService userServiceProxy = (UserService)JdkProxy.getInstance(userService);
        userServiceProxy.sayHello();
    }

    @Test
    public void test02(){
        UserService userService = new UserServiceImpl();
        CglibProxy cglibProxy = new CglibProxy(userService);
        //1.创建Enhancer对象
        Enhancer enhancer = new Enhancer();
//        2.设置父类：就是接口的实现类
        enhancer.setSuperclass(UserServiceImpl.class);
//        3.设置 method'Interceptor对象
        enhancer.setCallback(cglibProxy);
//        4.创建enhancer代理对象
        UserService o = (UserService)enhancer.create();
//        5.调用方法
        o.sayHello();
    }

    @Test
    public void test03(){
        final UserServiceImpl userService = new UserServiceImpl();
        UserService o =(UserService)Enhancer.create(userService.getClass(), new MethodInterceptor() {
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                System.out.println("--- cglib --- start ");
                method.invoke(userService, args);
                System.out.println("--- cglib --- end ");
                return null;
            }
        });
        o.sayHello();
    }

}
