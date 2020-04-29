package com.mypup.demo.models;




import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, columnDefinition = "INT(10) UNSIGNED")
    private long id;
    @Column(nullable = false, name = "user_role")
    private String userRole;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(columnDefinition = "VARCHAR(20)", name = "phone_number")
    private String phoneNumber;
    @Column(columnDefinition = "VARCHAR(20)")
    private String city;
    @Column(columnDefinition = "VARCHAR(20)")
    private String state;
    @Column(columnDefinition = "VARCHAR(12)")
    private String zipcode;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", orphanRemoval = true, fetch = FetchType.EAGER)
    private List<DogPost> dogPost;
    @ManyToMany(mappedBy = "users")
    private List<Breed> breeds;
    @Column(nullable = false, columnDefinition = "TEXT", name = "profile_image")
    private String profileImage;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Favorites> favorites;

    public List<Favorites> getFavorites() {
        return favorites;
    }

    public void setFavorites(List<Favorites> favorites) {
        this.favorites = favorites;
    }

    public List<Breed> getBreeds() {
        return breeds;
    }

    public void setBreeds(List<Breed> breeds) {
        this.breeds = breeds;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public User(String userRole, String username, String password, String email, String phoneNumber, String city, String state, String zipcode, List<DogPost> dogPost, List<Breed> breeds, String profileImage, List<Favorites> favorites) {
        this.userRole = userRole;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
        this.dogPost = dogPost;
        this.breeds = breeds;
        this.profileImage = profileImage;
        this.favorites = favorites;
    }
    public List<DogPost> getDogPost() {
        return dogPost;
    }

    public User() {}

    public User(User copy) {
        id = copy.id;
        username = copy.username;
        password = copy.password;
        email = copy.email;
        phoneNumber = copy.phoneNumber;
        city = copy.city;
        state = copy.state;
        zipcode = copy.zipcode;
        dogPost = copy.dogPost;
        userRole = copy.userRole;
        profileImage = copy.profileImage;
        favorites = copy.favorites;
    }
    public void setDogPost(List<DogPost> dogPost) {
        this.dogPost = dogPost;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }



}