package com.medoshin.lectures.javalab.springcore.dao;

import com.medoshin.lectures.javalab.springcore.entity.User;
import org.springframework.transaction.annotation.Transactional;

public interface UserDao {
    @Transactional
    User getUserById(Integer id);
}
