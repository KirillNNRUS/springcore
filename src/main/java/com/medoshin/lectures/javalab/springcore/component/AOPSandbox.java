package com.medoshin.lectures.javalab.springcore.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

public class AOPSandbox {
    @Autowired
    private AOPSandbox aopSandbox;

    @Autowired
    private ContextAware contextAware;

    @PostConstruct
    public void initIt() {
//        System.out.println("aopSundbox class: " + aopSandbox.getClass());
        aopSandbox.doAspect();
//        Timer timer = new Timer();
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                aopSandbox.doAspect();
//                timer.cancel();
//            }
//        }, 2000);
    }

    public void doAspect() {

    }

}
