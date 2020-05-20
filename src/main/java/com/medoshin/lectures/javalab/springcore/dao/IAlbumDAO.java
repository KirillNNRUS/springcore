package com.medoshin.lectures.javalab.springcore.dao;

import com.medoshin.lectures.javalab.springcore.entity.Album;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IAlbumDAO {
    @Transactional
    void add(Album album);

    @Transactional
    List<Album> getAll();

    @Transactional
    Album getById(Long id);

    @Transactional
    boolean isAlbumExist(String name);

    @Transactional
    long getIdByName(String name);

    @Transactional
    void update(String oldAlbumName, String newAlbumName);

    @Transactional
    void remove(Album album);
}
