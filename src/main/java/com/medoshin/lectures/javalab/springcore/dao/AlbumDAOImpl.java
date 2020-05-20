package com.medoshin.lectures.javalab.springcore.dao;

import com.medoshin.lectures.javalab.springcore.entity.Album;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    @Override
    public void add(Album album) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        if (!isAlbumExist(album.getAlbumName())) {
            entityManager.getTransaction().begin();
            entityManager.merge(album);
            entityManager.getTransaction().commit();
        }
        entityManager.close();
    }

    @Transactional
    @Override
    public List<Album> getAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Album> albumList = entityManager.createNamedQuery("Album.All", Album.class).getResultList();
        entityManager.close();
        return albumList;
    }

    @Transactional
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
            albumId = entityManager.createNamedQuery("Album.getByName", Album.class)
                    .setParameter("name", name.trim().toUpperCase())
                    .getSingleResult().getId();
        } catch (NoResultException e) {
            System.err.println(e.toString() + " Album " + name);
        } finally {
            entityManager.close();
        }

        return albumId;
    }

    @Transactional
    @Override
    public void update(String oldAlbumName, String newAlbumName) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Album newAlbum = null;
        try {
            entityManager.getTransaction().begin();
            newAlbum = entityManager.createNamedQuery("Album.getByName", Album.class)
                    .setParameter("name", oldAlbumName.trim().toUpperCase())
                    .getSingleResult();
            System.out.println(newAlbum);
            newAlbum.setAlbumName(newAlbumName);
            entityManager.merge(newAlbum);
            entityManager.getTransaction().commit();
        } catch (NoResultException e) {
            System.err.println(e.toString() + " Album name " + oldAlbumName);
        } finally {
            entityManager.close();
        }
    }

    @Transactional
    @Override
    public void remove(Album album) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Album delAlbum = null;
        try {
            entityManager.getTransaction().begin();
            delAlbum = entityManager.createNamedQuery("Album.getByName", Album.class)
                    .setParameter("name", album.getAlbumName())
                    .getSingleResult();
            entityManager.remove(delAlbum);
            entityManager.getTransaction().commit();
        } catch (NoResultException e) {
            System.err.println(e.toString() + " Album name " + album.getAlbumName());
        } finally {
            entityManager.close();
        }
    }
}
