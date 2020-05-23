package com.medoshin.lectures.javalab.springcore.dao;

import com.medoshin.lectures.javalab.springcore.entity.Song;
import com.medoshin.lectures.javalab.springcore.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import java.util.List;

@Component
public class UserDAOImpl implements IUserDAO {
    private EntityManagerFactory entityManagerFactory;

    public UserDAOImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Transactional
    @Override
    public void add(User user) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        if (!isUserExist(user.getName())) {
            entityManager.getTransaction().begin();
            entityManager.merge(user);
            entityManager.getTransaction().commit();
        }
        entityManager.close();
    }

    @Transactional
    @Override
    public List<User> getAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<User> userList = entityManager.createNamedQuery("User.All", User.class).getResultList();
        entityManager.close();
        return userList;
    }

    @Override
    public User getById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        User user = null;
        try {
            user = entityManager.createNamedQuery("User.getById", User.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (NoResultException e) {
            System.err.println(e.toString() + id);
        } finally {
            entityManager.close();
        }
        return user;
    }

    @Transactional
    @Override
    public boolean isUserExist(String name) {
        return getIdByName(name) != 0;
    }

    @Transactional
    @Override
    public long getIdByName(String name) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        long id = 0;
        try {
            id = entityManager.createNamedQuery("User.getByName", User.class)
                    .setParameter("name", name.trim().toUpperCase())
                    .getSingleResult().getId();
        } catch (NoResultException e) {
            System.err.println(e.toString() + name);
        } finally {
            entityManager.close();
        }
        return id;
    }

    /*
    Далее просто пустышки, я не могу найти и понять, как это сделать через JSON и/или используя Spring
     */
    @Transactional
    @Override
    public void updatePassword(User user, String newUserPassword) {

    }

    @Transactional
    @Override
    public void lock(User user) {

    }

    @Transactional
    @Override
    public void unLock(User user) {

    }

    @Transactional
    @Override
    public void addSongToUserSet(User user, Song... songs) {

    }

    @Transactional
    @Override
    public void removeSongFromUserSet(User user, Song... songs) {

    }
}
