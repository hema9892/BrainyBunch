package com.bookcatalouge.intelj.controller;

import com.bookcatalouge.intelj.dto.BookDTO;
import com.bookcatalouge.intelj.entity.Book;
import com.bookcatalouge.intelj.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/books")

public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping
    public BookDTO createBook(@RequestBody BookDTO book){
      return bookService.saveBook(book);
    }

    @GetMapping
    public List<Book> getAllBooks(){
        return bookService.findAllBooks();
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Long id){

        return bookService.findBooksById(id);
    }

   /* @PutMapping
    public Book updateBookRecord(@RequestBody BookDTO book) throws ChangeSetPersister.NotFoundException {
if (book == null || book.getTitle() ==null){
    throw new ChangeSetPersister.NotFoundException();
}
        Optional<Book> opt

    } */
 @GetMapping("/genre/{genreId}")
    public List<Book> getBookByGenre(@PathVariable Long genreId){
     return bookService.findBooksByGenre(genreId);
 }

 @GetMapping("/search")
    public List<Book> searchBooks(@RequestParam(required = false) String keyword){
     return bookService.searchBooks(keyword);
 }
 @PutMapping("/{id}")
    public BookDTO updateBook(@PathVariable Long id, @RequestBody BookDTO updatedBook){
     return bookService.updateBook(id, updatedBook);
 }
 @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id){
     bookService.deleteBook(id);
     return ResponseEntity.noContent().build();
 }
 @PostMapping("catalog/reserve/{bookId},{userId}")
    public ResponseEntity<String> reserveBook(
            @PathVariable Long bookId,
            @PathVariable Long userId,
            @RequestHeader("Authorization") String bearerToken){
                // @TODO JWT token validation logic
     // @TODO Book reservation logic
     // Authorization - "Bearer ehhhsssssssjjsjsj";
     //extract the actual token from the bearer string
     String jwtToken = bearerToken.substring(7);

     //validate the JWT token
     if (!bookService.validateJwtToken(jwtToken)){
     return new ResponseEntity<>("Invalid Token", HttpStatus.UNAUTHORIZED);
 }
     if (!bookService.reserveBook(bookId, userId)){
         return new ResponseEntity<>("Failed to Reserve book", HttpStatus.INTERNAL_SERVER_ERROR);
     }
return new ResponseEntity<>("Book reserved successfully", HttpStatus.OK);
}
}
