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
        if (!isSongExist(song.getSongName())) {
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
            System.err.println(e.toString() + " Song ID " + id);
        } finally {
            entityManager.close();
        }
        return song;
    }

    @Override
    public boolean isSongExist(String name) {
        return getIdByName(name) != 0;
    }

    @Override
    public long getIdByName(String name) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        long songId = 0;
        try {
            songId = entityManager.createNamedQuery("Song.getIdByName", Song.class)
                    .setParameter("name", name.trim().toUpperCase())
                    .getSingleResult().getId();
        } catch (NoResultException e) {
            System.err.println(e.toString() + " Song " + name);
        } finally {
            entityManager.close();
        }

        return songId;
    }

    @Override
    public Song update(Song song, String newSongName) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        song.setSongName(newSongName);
        entityManager.persist(song);
        entityManager.getTransaction().commit();
        entityManager.close();
        return song;
    }

    @Override
    public void remove(Song song) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.remove(song);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
