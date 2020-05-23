package com.medoshin.lectures.javalab.springcore.controller;

import com.medoshin.lectures.javalab.springcore.dao.IUserDAO;
import com.medoshin.lectures.javalab.springcore.entity.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/rest/users")
public class UserController {
    private IUserDAO iUserDAO;

    public UserController(IUserDAO iUserDAO) {
        this.iUserDAO = iUserDAO;
    }

    @PutMapping
    public void addAlbum(@RequestBody User user) {
        iUserDAO.add(user);
    }

    @GetMapping
    public List<User> getAllAlbums() {
        return iUserDAO.getAll();
    }

    @GetMapping("/{id}")
    public User getAlbumById(@PathVariable(name = "id") Long id) {
        return iUserDAO.getById(id);
    }
}
