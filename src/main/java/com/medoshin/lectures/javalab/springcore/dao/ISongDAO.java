package com.medoshin.lectures.javalab.springcore.dao;

import com.medoshin.lectures.javalab.springcore.entity.Song;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ISongDAO {
    @Transactional
    void add(Song song);

    @Transactional
    List<Song> getAll();

    @Transactional
    Song getById(Long id);

    @Transactional
    boolean isSongExist(String name);

    @Transactional
    long getIdByName(String name);

    @Transactional
    void update(String oldSongName, String newSongName);

    @Transactional
    void remove(Song song);
}
