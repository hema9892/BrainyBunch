package com.switz.loancontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.switz.loanentity.Book;
import com.switz.loanservice.BookService;

import dto.BookDTO;

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
 