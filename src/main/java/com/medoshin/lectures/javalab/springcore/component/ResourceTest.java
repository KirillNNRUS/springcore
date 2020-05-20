package com.medoshin.lectures.javalab.springcore.component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;

public class ResourceTest {
    private ContextAware contextAware;

    public ResourceTest(ContextAware contextAware) {
        this.contextAware = contextAware;
    }

    @PostConstruct
    public void initIt() throws IOException {
        System.out.println("-----path" + contextAware.getContext().getResource("classpath:t.txt").exists());
    }
}
