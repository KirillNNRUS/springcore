package com.medoshin.lectures.javalab.springcore._rawContext.component;

import javax.annotation.PostConstruct;

public class SimpleServiceImpl implements SimpleService {
    @PostConstruct
    @Override
    public void initIt() {
        System.out.println("--------init simple service");
    }
}
