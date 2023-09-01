package com.switz.loanrepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.switz.loanentity.Book;

import dto.BookDTO;

public interface Bookrepository extends JpaRepository<Book, Long>{
	
	

}
