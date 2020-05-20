package com.medoshin.lectures.javalab.springcore.component;

import com.medoshin.lectures.javalab.springcore.model.MessageEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;

import javax.annotation.PostConstruct;
import java.util.Timer;
import java.util.TimerTask;

public class EventPublisher {
//    @PostConstruct
//    public void initIt() {
//        new Timer().schedule(new TimerTask() {
//            @Override
//            public void run() {
//                doStuffAndPublishAnEvent("message");
//            }
//        }, 5000);
//    }

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void doStuffAndPublishAnEvent(final String message) {
        System.out.println("Publishing custom event. ");
        MessageEvent customSpringEvent = new MessageEvent(this, message);
        applicationEventPublisher.publishEvent(customSpringEvent);
    }
}
