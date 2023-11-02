package com.bookcatalouge.intelj.repository;

import com.bookcatalouge.intelj.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {


    List<Book> findBooksByGenre(Long genreId);
    List<Book> findByTitleContainingIgnoreCase(String title);
    List<Book> findByAuthorNameContainingIgnoreCase(String authorName);
    List<Book> findByGenreNameContainingIgnoreCase(String genreName);

    @Query("SELECT b FROM")
    List<Book> search(String keyword);
}
