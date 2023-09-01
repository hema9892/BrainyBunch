package com.switz.loanservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.switz.loanentity.Book;
import com.switz.loanrepository.Bookrepository;

import dto.BookDTO;

@Service
public class BookService {
	
	
	@Autowired
	private Bookrepository bookRepository;
	
	public Book saveBook(BookDTO bookDTO) {
    Book savedBook = new Book();
	savedBook.setTitle(bookDTO.getTitle());
	savedBook.setISBN(bookDTO.getISBN());
	return bookRepository.save(savedBook);
	
		
	}
	  public List<Book> findAllBooks() {
	        return bookRepository.findAll();
	    }
	  
	  public Book findBookById(Long id) {
		  return bookRepository.findById(id).orElse(null);
	  }
	  

		  
	  }

