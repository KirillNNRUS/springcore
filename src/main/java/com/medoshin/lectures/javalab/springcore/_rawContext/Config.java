package com.medoshin.lectures.javalab.springcore._rawContext;

import com.medoshin.lectures.javalab.springcore._rawContext.component.SimpleComponent;
import com.medoshin.lectures.javalab.springcore._rawContext.component.SimpleService;
import com.medoshin.lectures.javalab.springcore._rawContext.component.SimpleServiceImpl;
import com.medoshin.lectures.javalab.springcore._rawContext.component.SimpleServiceImpl2;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.medoshin.lectures.javalab.springcore._rawContext.component")
public class Config {
    @Bean(initMethod = "initMethod")
    public SimpleComponent simpleComponent(@Qualifier("simpleService2") SimpleService simpleService) {
        return new SimpleComponent(simpleService);
    }

    @Bean("simpleService")
    public SimpleService simpleService() {
        return new SimpleServiceImpl();
    }

    @Bean("simpleService2")
//    @Primary
    public SimpleService simpleService2() {
        return new SimpleServiceImpl2();
    }
}
