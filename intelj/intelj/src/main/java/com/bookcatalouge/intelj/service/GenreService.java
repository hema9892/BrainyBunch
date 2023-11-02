package com.bookcatalouge.intelj.service;

import com.bookcatalouge.intelj.dto.GenreDTO;
import com.bookcatalouge.intelj.entity.Genre;
import com.bookcatalouge.intelj.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreService {

    @Autowired
    private GenreRepository genreRepository;

public Genre saveGenre(GenreDTO genre){
    Genre genreDetails = new Genre();
    genreDetails.setName(genre.getName());
    genreDetails.setDescription(genre.getDescription());
   return genreRepository.save(genreDetails);
}
public List<Genre> findAllGenres(){
    return genreRepository.findAll();
}

public Genre findGenreById(Long id){
return genreRepository.findById(id).orElse(null);
}
}
