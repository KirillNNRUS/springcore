package com.medoshin.lectures.javalab.springcore._rawContext;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
//        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
//        context.register(Config.class);
//        context.refresh();
    }
}
