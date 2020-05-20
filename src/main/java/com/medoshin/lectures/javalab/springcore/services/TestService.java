package com.medoshin.lectures.javalab.springcore.services;

import com.medoshin.lectures.javalab.springcore.component.RequestBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.inject.Provider;

@Service
public class TestService {
    @Autowired
    private Provider<RequestBean> requestBeanProvider;

    public String getRequestBeanId() {
        return requestBeanProvider.get().getUuid();
    }
}
