package com.medoshin.lectures.javalab.springcore.dao;

import com.medoshin.lectures.javalab.springcore.entity.Song;

import java.util.List;

public interface ISongDAO {
    void add(Song song);

    List<Song> getAll();

    Song getById(Long id);

    boolean isSongExist(String name);

    long getIdByName(String name);

    Song update(Song song, String newSongName);

    void remove(Song song);
}
