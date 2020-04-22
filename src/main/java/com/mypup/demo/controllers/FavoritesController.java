package com.mypup.demo.controllers;


import com.mypup.demo.models.Favorites;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;

@Controller
public class FavoritesController {


    @PosttMapping("/favorites")
    public List<Favorites> favoritesList(@RequestBody HashMap<String,String> addFavoriteRequest){

    return addFavoriteRequest;
    }

    @GetMapping()
    remove from favorites

}
