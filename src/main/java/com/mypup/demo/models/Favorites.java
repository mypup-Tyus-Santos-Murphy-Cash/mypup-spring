package com.mypup.demo.models;


import com.mypup.demo.models.DogPost;
import com.mypup.demo.models.User;


import javax.persistence.*;

@Entity
@Table(name = "favorites")
public class Favorites {
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
    @Column(nullable = false, columnDefinition = "TEXT")
    private String images;
    @ManyToOne
//        (fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "dogPost_id")
    private DogPost dogPost;



    public Favorites() {
    }

    public Favorites(String dogBreed, String dogGroup, String dogDescription, String dogPrice, String images, User user, DogPost dogPost) {
        this.dogBreed = dogBreed;
        this.dogGroup = dogGroup;
        this.dogDescription = dogDescription;
        this.dogPrice = dogPrice;
        this.images = images;
        this.user = user;
        this.dogPost = dogPost;
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

    public DogPost getDogPost() {
        return dogPost;
    }

    public void setDogPost(DogPost dogPost) {
        this.dogPost = dogPost;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }
}