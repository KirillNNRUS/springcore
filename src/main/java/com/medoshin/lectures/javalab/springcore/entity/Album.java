package com.medoshin.lectures.javalab.springcore.entity;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table()
@NamedQueries({
        @NamedQuery(name = "Album.All", query = "SELECT a FROM Album a "),
        @NamedQuery(name = "Album.getById", query = "SELECT a FROM Album a WHERE a.id = :id"),
        @NamedQuery(name = "Album.getIdByName", query = "SELECT a FROM Album a WHERE a.albumName = :name"),
})
public class Album implements Serializable {
    public Album() {
    }

    public Album(String albumName) {
        this.albumName = albumName.trim().toUpperCase();
    }

    @Id
    @Column()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String albumName;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName.trim().toUpperCase();
    }

    @Override
    public String toString() {
        return "Album{" +
                "id=" + id +
                ", albumName='" + albumName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Album album = (Album) o;
        return id == album.id &&
                albumName.equals(album.albumName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, albumName);
    }
}
