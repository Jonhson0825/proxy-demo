package com.doit.proxy;

import com.doit.service.UserService;
import com.doit.service.impl.UserServiceImpl;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JdkProxy implements InvocationHandler {

    private Object target;

    public JdkProxy(Object target){
        this.target = target;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("Before invoke "  + method.getName());
        method.invoke(target, args);
        System.out.println("After invoke " + method.getName());
        return null;
    }

    public static Object getInstance(Object target){
        JdkProxy jdkProxy = new JdkProxy(target);
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),target.getClass().getInterfaces(),jdkProxy);
    }
}

