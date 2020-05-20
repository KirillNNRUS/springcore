package com.medoshin.lectures.javalab.springcore.entity;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table()
@NamedQueries({
        @NamedQuery(name = "Song.All", query = "SELECT s FROM Song s "),
        @NamedQuery(name = "Song.getById", query = "SELECT s FROM Song s WHERE s.id = :id"),
        @NamedQuery(name = "Song.getIdByName", query = "SELECT s FROM Song s WHERE s.songName = :name"),
})
public class Song implements Serializable {
    public Song() {
    }

    public Song(String songName) {
        this.songName = songName.trim().toUpperCase();
    }

    @Id
    @Column()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String songName;

    @ManyToOne(cascade = CascadeType.ALL)
    private Album album;

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName.trim().toUpperCase();
    }

    @Override
    public String toString() {
        return "Song{" +
                "id=" + id +
                ", songName='" + songName + '\'' +
                ", album=" + album +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Song song = (Song) o;
        return id == song.id &&
                songName.equals(song.songName) &&
                Objects.equals(album, song.album);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, songName, album);
    }
}
