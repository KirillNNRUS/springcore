package com.medoshin.lectures.javalab.springcore.component;

import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

public class EnvAware implements EnvironmentAware {
    private Environment environment;

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    public Environment getEnvironment() {
        return environment;
    }
}
