package com.medoshin.lectures.javalab.springcore.dao;

import com.medoshin.lectures.javalab.springcore.entity.Uzer;
import org.springframework.transaction.annotation.Transactional;

public interface UzerDAO {
    @Transactional
    Uzer getUserById(Integer id);
}
