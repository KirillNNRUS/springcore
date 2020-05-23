package com.medoshin.lectures.javalab.springcore.dao;

import com.medoshin.lectures.javalab.springcore.entity.Song;
import com.medoshin.lectures.javalab.springcore.entity.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IUserDAO {
    @Transactional
    void add(User user);

    @Transactional
    List<User> getAll();

    @Transactional
    User getById(Long id);

    @Transactional
    boolean isUserExist(String name);

    @Transactional
    long getIdByName(String name);

    @Transactional
    void updatePassword(User user, String newUserPassword);

    @Transactional
    void lock(User user);

    @Transactional
    void unLock(User user);

    @Transactional
    void addSongToUserSet(User user, Song... songs);

    @Transactional
    void removeSongFromUserSet(User user, Song... songs);
}
