package com.medoshin.lectures.javalab.springcore.entity;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table()
@NamedQueries({
        @NamedQuery(name = "Album.All", query = "SELECT a FROM Album a "),
        @NamedQuery(name = "Album.getById", query = "SELECT a FROM Album a WHERE a.id = :id"),
        @NamedQuery(name = "Album.getByName", query = "SELECT a FROM Album a WHERE a.name = :name"),
})
public class Album implements Serializable {
    public Album() {
    }

    public Album(String name) {
        this.name = name.trim().toUpperCase();
    }

    @Id
    @Column()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String albumName) {
        this.name = albumName.trim().toUpperCase();
    }

    @Override
    public String toString() {
        return "Album{" +
                "id=" + id +
                ", albumName='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Album album = (Album) o;
        return id == album.id &&
                name.equals(album.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
