package com.mypup.demo.models;

import org.springframework.stereotype.Indexed;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "dog_posts")
public class DogPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, columnDefinition = "INT(10) UNSIGNED")
    private long id;
    @Column(columnDefinition = "VARCHAR(200)", name = "dog_breed")
    private String dogBreed;
    @Column(nullable = false, columnDefinition = "VARCHAR(200)", name = "dog_group")
    private String dogGroup;
    @Column(columnDefinition = "TEXT", name = "dog_description")
    private String dogDescription;
    @Column(columnDefinition = "VARCHAR(45)", name = "dog_price")
    private String dogPrice;
    @Column(columnDefinition = "VARCHAR(45)", name = "heart")
    private String heart;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name="user_id")
    private User user;
    @ManyToMany
    @JoinTable(
            name="dog_post_has_breed",
            joinColumns={@JoinColumn(name="dog_post_id")},
            inverseJoinColumns={@JoinColumn(name="breed_id")}
    )
    private List<Breed> breeds;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String images;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name="favorites",
            joinColumns={@JoinColumn(name="dog_post_id")},
            inverseJoinColumns={@JoinColumn(name="user_id")}
    )

    private Set<User> favorites = new HashSet<>();

    public DogPost(String dogBreed, String dogGroup, String dogDescription, String dogPrice, User user, List<Breed> breeds, String images, Set<User> favorites, String heart) {
        this.dogBreed = dogBreed;
        this.dogGroup = dogGroup;
        this.dogDescription = dogDescription;
        this.dogPrice = dogPrice;
        this.breeds = breeds;
        this.user = user;
        this.images = images;
        this.favorites = favorites;
        this.heart = heart;
    }

    public String getHeart() {
        return heart;
    }

    public void setHeart(String heart) {
        this.heart = heart;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public DogPost() {}

    public List<Breed> getBreeds() {
        return breeds;
    }

    public void setBreeds(List<Breed> breeds) {
        this.breeds = breeds;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDogBreed() {
        return dogBreed;
    }

    public void setDogBreed(String dogBreed) {
        this.dogBreed = dogBreed;
    }

    public String getDogGroup() {
        return dogGroup;
    }

    public void setDogGroup(String dogGroup) {
        this.dogGroup = dogGroup;
    }

    public String getDogDescription() {
        return dogDescription;
    }

    public void setDogDescription(String dogDescription) {
        this.dogDescription = dogDescription;
    }

    public String getDogPrice() {
        return dogPrice;
    }

    public void setDogPrice(String dogPrice) {
        this.dogPrice = dogPrice;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<User> getFavorites() {
        return favorites;
    }

    public void setFavorites(Set<User> favorites) {
        this.favorites = favorites;
    }
}