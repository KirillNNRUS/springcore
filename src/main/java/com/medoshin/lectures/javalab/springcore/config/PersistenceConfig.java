package com.medoshin.lectures.javalab.springcore.config;

import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.persistence.EntityManagerFactory;
import java.util.HashMap;
import java.util.Properties;

@Configuration
public class PersistenceConfig {
    org.springframework.core.env.Environment env;

    public PersistenceConfig(org.springframework.core.env.Environment env) {
        this.env = env;
    }

    @Bean("entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        entityManagerFactory.setJpaDialect(new HibernateJpaDialect());
        entityManagerFactory.setPackagesToScan("com.medoshin.lectures.javalab.springcore.entity");

        entityManagerFactory.setJpaProperties(hibernateJpaProperties());
        return entityManagerFactory;
    }

    @Bean
    public JpaTransactionManager transactionManager(@Qualifier("entityManagerFactory") EntityManagerFactory emf) {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(emf);
        return jpaTransactionManager;
    }

    private Properties hibernateJpaProperties() {
        HashMap<String, String> properties = new HashMap<>();
        Properties props = new Properties();
        props.put(Environment.DRIVER, env.getProperty("spring.datasource.driverClassName"));
        props.put(Environment.URL, env.getProperty("spring.datasource.url"));
        props.put(Environment.SHOW_SQL, "true");
        props.put(Environment.HBM2DDL_AUTO, "create");
        props.put(Environment.USER, env.getProperty("spring.datasource.username"));
        props.put(Environment.PASS, env.getProperty("spring.datasource.password"));
        return props;
    }
}
