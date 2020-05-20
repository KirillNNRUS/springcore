package com.medoshin.lectures.javalab.springcore.controller;

import com.medoshin.lectures.javalab.springcore.dao.UserDao;
import com.medoshin.lectures.javalab.springcore.entity.User;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(path = "/users")
public class UserController {
    private UserDao userDao;

    @GetMapping
    public List<User> getUsers() {
        return Collections.emptyList();
    }

    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }

    @PostMapping
    public void addUser(@RequestBody User user) {
        System.out.println("----- added user from demo controller: " + user);
    }

    @GetMapping(path = "/{id}")
//    @ResponseBody
    public User getUserById(@PathVariable(name = "id") Integer id) {
        return userDao.getUserById(id);
    }
}
