package com.ULB.service.service;

import com.ULB.service.dto.BookDTO;
import com.ULB.service.entity.Book;
import com.ULB.service.entity.BookStatus;
import com.ULB.service.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public Book saveBook(BookDTO bookDTO) {
        Book savedBook = new Book();
        savedBook.setTitle(bookDTO.getTitle());
        savedBook.setISBN(bookDTO.getISBN());
        savedBook.setStatus(BookStatus.AVAILABLE);
        return bookRepository.save(savedBook);


    }
    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    public Book findBookById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }


}
