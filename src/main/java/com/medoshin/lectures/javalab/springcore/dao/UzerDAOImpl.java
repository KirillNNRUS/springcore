package com.medoshin.lectures.javalab.springcore.dao;

import com.medoshin.lectures.javalab.springcore.entity.Uzer;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class UzerDAOImpl implements UzerDAO {
    private EntityManagerFactory entityManagerFactory;

    public UzerDAOImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Transactional
    @Override
    public Uzer getUserById(Integer id) {
        EntityManager em = entityManagerFactory.createEntityManager();
//        em.getTransaction().begin();
        Uzer result = (Uzer) em
                .createQuery("From User as u where u.id= ?1", Uzer.class)
                .setParameter(1, id)
                .getResultList().get(0);
//        em.getTransaction().commit();
        em.close();
        return result;
    }
}
