package com.mypup.demo.controllers;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;

@Controller

public class FavoritesController {

    @RequestMapping("favorites")
    public ResponseEntity<?> Favorites(@RequestBody HashMap<String,String> addCartRequest){


    }


}
