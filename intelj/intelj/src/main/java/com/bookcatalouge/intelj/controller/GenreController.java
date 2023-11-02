package com.bookcatalouge.intelj.controller;

import com.bookcatalouge.intelj.dto.GenreDTO;
import com.bookcatalouge.intelj.entity.Genre;
import com.bookcatalouge.intelj.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/genres")
public class GenreController {

    @Autowired
    private GenreService genreService;

    @PostMapping
    public Genre createGenre(@RequestBody GenreDTO genre) {
        return genreService.saveGenre(genre);
    }

    @GetMapping
    public List<Genre> getAllGenres() {
        return genreService.findAllGenres();
    }

    @GetMapping("/{id}")
    public Genre getGenreById(@PathVariable Long id) {
        return genreService.findGenreById(id);
    }
}
