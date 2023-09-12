package com.ULB.service.controller;

import com.ULB.service.dto.BookDTO;
import com.ULB.service.entity.Book;
import com.ULB.service.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping
    public Book createBook(@RequestBody BookDTO book) {
        return bookService.saveBook(book);

    }


    @GetMapping
    public List<Book> getAllBooks(){
        return bookService.findAllBooks();

    }
    @GetMapping("/{Id}")
    public Book getBookById(@PathVariable Long id) {
        return bookService.findBookById(id);
    }
}
