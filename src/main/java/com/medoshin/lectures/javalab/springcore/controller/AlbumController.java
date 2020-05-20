package com.medoshin.lectures.javalab.springcore.controller;

import com.medoshin.lectures.javalab.springcore.dao.IAlbumDAO;
import com.medoshin.lectures.javalab.springcore.entity.Album;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/albums")
public class AlbumController {
    private IAlbumDAO iAlbumDAO;

    public AlbumController(IAlbumDAO iAlbumDAO) {
        this.iAlbumDAO = iAlbumDAO;
    }

    @PostMapping
    public void addUser(@RequestBody Album album) {
        iAlbumDAO.add(album);
    }

    @GetMapping
    public List<Album> getAllAlbums() {
        return iAlbumDAO.getAll();
    }
}
