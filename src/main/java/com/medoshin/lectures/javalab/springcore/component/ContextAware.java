package com.medoshin.lectures.javalab.springcore.component;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class ContextAware implements ApplicationContextAware {
    private ApplicationContext context = null;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (context != null) {
            return;
        }
        this.context = applicationContext;
    }

    public ApplicationContext getContext() {
        return context;
    }
}
