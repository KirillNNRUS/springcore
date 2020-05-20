package com.medoshin.lectures.javalab.springcore.controller;

import com.medoshin.lectures.javalab.springcore.dao.ISongDAO;
import com.medoshin.lectures.javalab.springcore.entity.Song;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/songs")
public class SongController {
    private ISongDAO iSongDAO;

    public SongController(ISongDAO iSongDAO) {
        this.iSongDAO = iSongDAO;
    }

    @PutMapping
    public void addSong(@RequestBody Song song) {
        iSongDAO.add(song);
    }

    @GetMapping
    public List<Song> getAllAlbums() {
        return iSongDAO.getAll();
    }

    @GetMapping("/{id}")
    public Song getSongById(@PathVariable(name = "id") Long id) {
        return iSongDAO.getById(id);
    }

    @PatchMapping
    public Song update(@RequestBody Song song, String newAlbumName) {
        return iSongDAO.update(song, newAlbumName);
    }

    @DeleteMapping
    void deleteSong(@RequestBody Song song) {
        iSongDAO.remove(song);
    }

}
