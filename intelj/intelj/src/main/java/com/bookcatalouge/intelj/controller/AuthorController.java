package com.bookcatalouge.intelj.controller;

import com.bookcatalouge.intelj.dto.AuthorDTO;
import com.bookcatalouge.intelj.entity.Author;
import com.bookcatalouge.intelj.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @PostMapping
        public Author createAuthor(@RequestBody AuthorDTO author ){

        return authorService.saveAuthor(author);
    }

    @GetMapping
    public List<Author> getAllAuthors(){

        return authorService.findAllAuthors();
    }

    @GetMapping("/{id}")
    public Author getAuthorbyId(@PathVariable Long id){

        return authorService.findAuthorById(id);
    }

}
