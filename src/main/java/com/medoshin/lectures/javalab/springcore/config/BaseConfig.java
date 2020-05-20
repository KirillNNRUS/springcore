package com.medoshin.lectures.javalab.springcore.config;

import com.medoshin.lectures.javalab.springcore.dao.UserDao;
import com.medoshin.lectures.javalab.springcore.dao.UserDaoImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import javax.persistence.EntityManagerFactory;

@Configuration
@PropertySource("classpath:added-props.properties")
public class BaseConfig {
    private Environment environment;

    private String addedProps;

    public BaseConfig(Environment environment) {
        this.environment = environment;
    }

    @Bean
    public UserDao userDao(EntityManagerFactory entityManagerFactory) {
        return new UserDaoImpl(entityManagerFactory);
    }
}
