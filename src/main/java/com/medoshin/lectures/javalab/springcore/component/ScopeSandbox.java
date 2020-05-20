package com.medoshin.lectures.javalab.springcore.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Provider;

@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class ScopeSandbox {
    @Autowired
    ScopeSandbox scopeSandbox;

    @Autowired
    Provider<Prototype> prototypeProvider;

//    @Autowired
//    Prototype prototype;

    @Autowired
    RequestBean requestBean;

    @PostConstruct
    public void initIt() {
//        Prototype proto_1 = prototypeProvider.get();
//        Prototype proto_2 = prototypeProvider.get();
        Prototype proto_1 = prototypeProvider.get();
        Prototype proto_2 = prototypeProvider.get();
        System.out.println("scope sandbox class: " + scopeSandbox.getClass());
        System.out.println("proto_1 class: " + proto_1.getClass());
        System.out.println("proto_2 class: " + proto_2.getClass());
        System.out.println("proto_1 object: " + proto_1);
        System.out.println("proto_2 object: " + proto_2);
//        System.out.println("req_1 class: " + reqBean_1.getClass());
//        System.out.println("req_2 class: " + reqBean_2.getClass());
    }
}
