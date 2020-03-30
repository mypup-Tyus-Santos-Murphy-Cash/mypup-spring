package com.mypup.demo.models;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "image")
public class Images {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, columnDefinition = "INT UNSIGNED")
    private long id;
    @Column(nullable = false)
    private String location;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name="pivot_image",
            joinColumns={@JoinColumn(name="image_id")},
            inverseJoinColumns={@JoinColumn(name="dog_post_id")}
    )
    private List<DogPost> dogPosts;

    public Images() {}

    public Images(String location, List<DogPost> dogPosts) {
        this.location = location;
        this.dogPosts = dogPosts;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<DogPost> getDogPosts() {
        return dogPosts;
    }

    public void setDogPosts(List<DogPost> dogPosts) {
        this.dogPosts = dogPosts;
    }
}
