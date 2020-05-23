package com.medoshin.lectures.javalab.springcore.dao;

import com.medoshin.lectures.javalab.springcore.entity.Song;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import java.util.List;

@Component
public class SongDAOImpl implements ISongDAO {
    private EntityManagerFactory entityManagerFactory;

    public SongDAOImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Transactional
    @Override
    public void add(Song song) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        if (!isSongExist(song.getName())) {
            entityManager.getTransaction().begin();
            entityManager.merge(song);
            entityManager.getTransaction().commit();
        }
        entityManager.close();
    }

    @Transactional
    @Override
    public List<Song> getAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Song> songList = entityManager.createNamedQuery("Song.All", Song.class).getResultList();
        entityManager.close();
        return songList;
    }

    @Transactional
    @Override
    public Song getById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Song song = null;
        try {
            song = entityManager.createNamedQuery("Song.getById", Song.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (NoResultException e) {
            System.err.println(e.toString() + id);
        } finally {
            entityManager.close();
        }
        return song;
    }

    @Transactional
    @Override
    public boolean isSongExist(String name) {
        return getIdByName(name) != 0;
    }

    @Transactional
    @Override
    public long getIdByName(String name) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        long id = 0;
        try {
            id = entityManager.createNamedQuery("Song.getByName", Song.class)
                    .setParameter("name", name.trim().toUpperCase())
                    .getSingleResult().getId();
        } catch (NoResultException e) {
            System.err.println(e.toString() + name);
        } finally {
            entityManager.close();
        }
        return id;
    }

    @Transactional
    @Override
    public void update(String oldSongName, String newSongName) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Song newSong = null;
        try {
            entityManager.getTransaction().begin();
            newSong = entityManager.createNamedQuery("Song.getByName", Song.class)
                    .setParameter("name", oldSongName.trim().toUpperCase())
                    .getSingleResult();
            System.out.println(newSong);
            newSong.setName(newSongName);
            entityManager.merge(newSong);
            entityManager.getTransaction().commit();
        } catch (NoResultException e) {
            System.err.println(e.toString() + oldSongName);
        } finally {
            entityManager.close();
        }
    }

    @Transactional
    @Override
    public void remove(Song song) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Song delSong = null;
        try {
            entityManager.getTransaction().begin();
            delSong = entityManager.createNamedQuery("Song.getByName", Song.class)
                    .setParameter("name", song.getName())
                    .getSingleResult();
            entityManager.remove(delSong);
            entityManager.getTransaction().commit();
        } catch (NoResultException e) {
            System.err.println(e.toString() + song.getName());
        } finally {
            entityManager.close();
        }
    }
}
