package com.medoshin.lectures.javalab.springcore._rawContext.component;

import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class SimpleComponent {
    private SimpleService simpleService;

    @Autowired
    public SimpleComponent(SimpleService simpleService) {
        this.simpleService = simpleService;
    }

    public void initMethod() {
        System.out.println("-------init method");
    }

    @PostConstruct
    public void initIt() {
        System.out.println("-----------init simple component");
    }

    @PreDestroy
    public void destroy() {

    }

}
