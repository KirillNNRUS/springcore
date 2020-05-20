package com.medoshin.lectures.javalab.springcore.component;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

//@Component
//@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Prototype {
    @PostConstruct
    public void initIt() {
    }

//    @Lookup
//    fun validateBean(): ValidateBean? {
//        return null
//    }
}
