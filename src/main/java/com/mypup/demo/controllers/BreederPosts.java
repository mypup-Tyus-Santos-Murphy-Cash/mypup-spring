package com.mypup.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BreederPosts {

    private PostsRepo breederPostDao;
    private UserRepo userDao;

    public BreederPosts(PostsRepo breederPostDao, UserRepo userDao) {
        this.breederPostDao = breederPostDao;
        this.UserDao = userDao;
    }

    @GetMapping("/breeder-posts")
    public String getBreederPosts(Model model){
        model.addAttribute("breeder-posts", breederPostDao.findAll());
        return "breeder-posts/index";
    }


    @GetMapping ("/breeder-posts/{id}")
    public String getBreederPosts(@PathVariable long id, Model model){
        model.addAttribute("breeder-post", breederPostDao.getOne(id));
        return "breeder-post/show";
    }

    @GetMapping("/breeder-posts/create")
    public String getCreatedBreederPostForm(Model model){
        model.addAttribute("breeder-posts", new BreederPosts());
        return "breeder-posts/create";
    }
    @PostMapping("/breeder-posts/create")
    public String createBreederPost(@ModelAttribute BreederPosts breederPosts){
        breederPosts.setUser(userDao.getOne(1L));
        breederPostDao.save(breederPosts);
        return "redirect:/breeder-posts";
    }

    @PostMapping("/breeder-posts/{id}/delete")
    public String deleteBreederPost(@PathVariable long id){
        // delete post
        breederPostDao.deleteById(id);
        return "redirect:/breeder-posts";
    }
    @GetMapping("/breeder-posts/{id}/update")
    public String editBreederPostForm(@PathVariable long id, Model model) {
//        BreederPosts breederPostToEdit = breederPostDao.getOne(id);
        model.addAttribute("breeder-post", breederPostToEdit);
        return "breeder-posts/update";
    }


    @PostMapping("/breeder-posts/{id}/update")
    public String updateBreederPost(@PathVariable long id, @ModelAttribute BreederPosts bp) {
        bp = breederPostDao.getOne(id);
        bp.setTitle(breederPosts.getTitle());
        bp.setBody(breederPosts.getBody());
        breederPostDao.save(bp);
        return "redirect:/breeder-post";
    }


}


