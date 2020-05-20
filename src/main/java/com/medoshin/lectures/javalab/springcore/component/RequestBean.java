package com.medoshin.lectures.javalab.springcore.component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

public class RequestBean {
    private String uuid;

    public RequestBean() {
        setUuid(UUID.randomUUID().toString());
    }

    @PostConstruct
    public void initIt() {
        System.out.println("++++++++++++++++++++++++request bean init");
        System.out.println("---------post constract id: " + getUuid());
    }

    @PreDestroy
    public void destroy() {
        System.out.println("++++++++++++++++++++++++request bean destroy");
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
