package com.mypup.demo.controllers;


import com.mypup.demo.models.DogPost;
import com.mypup.demo.models.Favorites;
import com.mypup.demo.repos.DogPostRepo;
import com.mypup.demo.repos.FavoritesRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@Controller
public class FavoritesController {
    private DogPostRepo dogPostDao;
    private FavoritesRepo favoritesDao;

    public FavoritesController(DogPostRepo dogPostDao, FavoritesRepo favoritesDao) {
        this.dogPostDao = dogPostDao;
        this.favoritesDao = favoritesDao;
    }

    @PostMapping("/buyer-profile/{id}/addToFavorites")
    public String addToFavorites(@PathVariable long id, @RequestParam String dogBreed, @RequestParam String dogGroup, @RequestParam String dogDescription, @RequestParam String dogPrice, @RequestParam String images) {
        Favorites addToFavorites = favoritesDao.getOne(id);
        addToFavorites.setDogBreed(dogBreed);
        addToFavorites.setDogGroup(dogGroup);
        addToFavorites.setDogDescription(dogDescription);
        addToFavorites.setDogPrice(dogPrice);
        addToFavorites.setImages(images);
        favoritesDao.save(addToFavorites);
        return "redirect:/buyer-profile";
    }

    @GetMapping()
    public String removeFromFavorites(){

        return "redirect:/buyer-profile";
    }

}
