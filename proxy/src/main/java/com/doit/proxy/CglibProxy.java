package com.doit.proxy;

import com.doit.service.UserService;
import com.doit.service.impl.UserServiceImpl;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibProxy implements MethodInterceptor {

    private Object target;

    public CglibProxy(Object obj){
        this.target = obj;
    }

    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("CG Before invoke "  + method.getName());
        method.invoke(target, args);
        System.out.println("CG After invoke " + method.getName());
        return null;
    }
}