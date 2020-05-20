package com.medoshin.lectures.javalab.springcore.services;

import com.medoshin.lectures.javalab.springcore.model.MessageEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;

import javax.annotation.PostConstruct;
import java.util.Timer;
import java.util.TimerTask;


//public class MessageListener implements ApplicationListener<MessageEvent> { //without annotation
public class MessageListener {
//    @Override //without annotation
    @EventListener
    public void onApplicationEvent(MessageEvent event) {
        System.out.println("------from event  " + event.getMsg());
    }
}
