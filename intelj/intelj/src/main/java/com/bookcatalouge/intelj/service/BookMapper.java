package com.bookcatalouge.intelj.service;

import com.bookcatalouge.intelj.dto.BookDTO;
import com.bookcatalouge.intelj.entity.*;
import com.bookcatalouge.intelj.repository.AuthorRepository;
import com.bookcatalouge.intelj.repository.BookRepository;
import com.bookcatalouge.intelj.repository.GenreRepository;
import com.bookcatalouge.intelj.repository.PublisherRepository;
import javax.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;

public class BookMapper {
    @Autowired
    private BookRepository bookRepository;
   @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private PublisherRepository publisherRepository;

    @Autowired
    private GenreRepository genreRepository;

    public Book toEntity(BookDTO dto){
Author author = authorRepository.findById(dto.getAuthorId())
        .orElseThrow(()-> new EntityNotFoundException("Author not found"));

Genre genre = genreRepository.getById(dto.getGenreId());

Publisher publisher = publisherRepository.getById(dto.getPublisherId());

Book book = Book.builder()
             .title(dto.getTitle())
             .ISBN(dto.getISBN())
             .publicationDate(dto.getPublicationDate())
        .rating(dto.getRating())
        .status(BookStatus.valueOf(dto.getStatus()))
        .summary(dto.getSummary())
        .coverImage(dto.getCoverImage())
        .author(author)
        .genre(genre)
        .publisher(publisher)
        .build();

return book;

    }
    public BookDTO toDTO(Book book){
        BookDTO dto = BookDTO.builder()
                .title(book.getTitle())
                .ISBN(book.getISBN())
                .authorId(book.getAuthor().getId())
                .genreId(book.getGenre().getId())
                .publisherId(book.getPublisher().getId())
                .publicationDate(book.getPublicationDate())
                .summary(book.getSummary())
                .coverImage(book.getCoverImage())
                .rating(book.getRating())
                .status(book.getStatus().toString())
                .build();
        return dto;
    }
}
