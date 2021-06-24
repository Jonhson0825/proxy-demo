package com.doit.proxy;

import com.doit.service.UserService;
import com.doit.service.impl.UserServiceImpl;

public class User implements UserService {

    private UserService userServiceImpl = new UserServiceImpl();

    public void sayHello() {
        System.out.println("静态代理 start");
        userServiceImpl.sayHello();
        System.out.println("静态代理 end");
    }
}
