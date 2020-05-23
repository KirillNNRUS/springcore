package com.medoshin.lectures.javalab.springcore.entity;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Entity
@Table()
@NamedQueries({
        @NamedQuery(name = "User.All", query = "SELECT u FROM User u"),
        @NamedQuery(name = "User.getById", query = "SELECT u FROM User u WHERE u.id = : id"),
        @NamedQuery(name = "User.getByName", query = "SELECT u FROM User u WHERE u.name = : name"),
})
public class User implements Serializable {
    public User() {
        this.isLocked = false;
    }

    @Id
    @Column()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column()
    private String password;

    @Column(nullable = false)
    private boolean isLocked;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Song> songs;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setLocked(boolean locked) {
        isLocked = locked;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public Set<Song> getSongs() {
        return songs;
    }

    public void setSongs(Set<Song> songs) {
        this.songs = songs;
    }

    public String getName() {
        return name;
    }

    public void setName(String userName) {
        this.name = userName.trim().toUpperCase();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + name + '\'' +
                ", password='" + password + '\'' +
                ", isLocked=" + isLocked +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return isLocked == user.isLocked &&
                name.equals(user.name) &&
                Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, password, isLocked);
    }
}
