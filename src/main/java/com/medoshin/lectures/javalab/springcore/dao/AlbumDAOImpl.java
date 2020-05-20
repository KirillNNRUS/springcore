package com.medoshin.lectures.javalab.springcore.dao;

import com.medoshin.lectures.javalab.springcore.entity.Album;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import java.util.List;

@Component
public class AlbumDAOImpl implements IAlbumDAO {
    private EntityManagerFactory entityManagerFactory;

    public AlbumDAOImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public void add(Album album) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(album);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public List<Album> getAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Album> albumList = entityManager.createNamedQuery("Album.All", Album.class).getResultList();
        entityManager.close();
        return albumList;
    }

    @Override
    public Album getById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Album album = null;
        try {
            album = entityManager.createNamedQuery("Album.getById", Album.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (NoResultException e) {
            System.err.println(e.toString() + " Album ID " + id);
        } finally {
            entityManager.close();
        }
        return album;
    }

    @Override
    public boolean isAlbumExist(String name) {
        return getIdByName(name) != 0;
    }

    @Override
    public long getIdByName(String name) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        long albumId = 0;
        try {
            albumId = entityManager.createNamedQuery("Album.getIdByName", Album.class)
                    .setParameter("name", name.trim().toUpperCase())
                    .getSingleResult().getId();
        } catch (NoResultException e) {
            System.err.println(e.toString() + " Album " + name);
        } finally {
            entityManager.close();
        }

        return albumId;
    }

    @Override
    public void update(Album album, String newAlbumName) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        album.setAlbumName(newAlbumName);
        entityManager.persist(album);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void remove(Album album) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.remove(album);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
