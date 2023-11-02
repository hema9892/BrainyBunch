package com.bookcatalouge.intelj.service;

import com.bookcatalouge.intelj.dto.AuthorDTO;
import com.bookcatalouge.intelj.entity.Author;
import com.bookcatalouge.intelj.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;


    public Author saveAuthor(AuthorDTO author){
        Author authorDetails = new Author();
        authorDetails.setName(author.getName());
        authorDetails.setBiography(author.getBiography());
        authorDetails.setProfileImage(author.getProfileImage());
        return authorRepository.save(authorDetails);

    }

    public List<Author> findAllAuthors(){
       return authorRepository.findAll();
    }
   public Author findAuthorById(Long id){
        return authorRepository.findById(id).orElse(null);
   }
}
