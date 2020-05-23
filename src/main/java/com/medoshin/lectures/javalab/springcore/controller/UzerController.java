package com.medoshin.lectures.javalab.springcore.controller;

import com.medoshin.lectures.javalab.springcore.dao.UzerDAO;
import com.medoshin.lectures.javalab.springcore.entity.Uzer;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(path = "/uzers")
public class UzerController {
    private UzerDAO uzerDAO;

    @GetMapping
    public List<Uzer> getUsers() {
        return Collections.emptyList();
    }

    public UzerController(UzerDAO uzerDAO) {
        this.uzerDAO = uzerDAO;
    }

    @PostMapping
    public void addUser(@RequestBody Uzer user) {
        System.out.println("----- added user from demo controller: " + user);
    }

    @GetMapping(path = "/{id}")
//    @ResponseBody
    public Uzer getUserById(@PathVariable(name = "id") Integer id) {
        return uzerDAO.getUserById(id);
    }
}
