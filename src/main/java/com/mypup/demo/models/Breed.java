package com.mypup.demo.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "breed")
public class Breed {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, columnDefinition = "INT UNSIGNED")
    private long id;
    @Column(nullable = false, unique = true)
    private String name;
    @ManyToMany(mappedBy = "breeds")
    private List<DogPost> dogPosts;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name="breed_selection",
            joinColumns={@JoinColumn(name="breed_id")},
            inverseJoinColumns={@JoinColumn(name="user_id")}
    )
    private List<User> users;

    public Breed() {}

    public Breed(String name, List<DogPost> dogPosts, List<User> users) {
        this.name = name;
        this.dogPosts = dogPosts;
        this.users = users;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<DogPost> getDogPosts() {
        return dogPosts;
    }

    public void setDogPosts(List<DogPost> dogPosts) {
        this.dogPosts = dogPosts;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}