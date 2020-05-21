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

    @PutMapping
    public void addAlbum(@RequestBody Album album) {
        iAlbumDAO.add(album);
    }

    @GetMapping
    public List<Album> getAllAlbums() {
        return iAlbumDAO.getAll();
    }

    @GetMapping("/{id}")
    public Album getAlbumById(@PathVariable(name = "id") Long id) {
        return iAlbumDAO.getById(id);
    }

    /*
    Конечно извращение, но как передать это через JSON пока не нашел
     */
    @PatchMapping("/{oldName}-{newName}")
    public void update(@PathVariable(name = "oldName") String oldName, @PathVariable(name = "newName") String newName) {
        iAlbumDAO.update(oldName, newName);
    }

    @DeleteMapping
    void deleteBook(@RequestBody Album album) {
        iAlbumDAO.remove(album);
    }

}
