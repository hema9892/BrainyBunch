package com.bookcatalouge.intelj.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.bookcatalouge.intelj.dto.BookDTO;
import com.bookcatalouge.intelj.entity.Book;
import com.bookcatalouge.intelj.entity.BookStatus;
import com.bookcatalouge.intelj.entity.Reservation;
import com.bookcatalouge.intelj.exception.BookNotFoundException;
import com.bookcatalouge.intelj.repository.BookRepository;
import com.bookcatalouge.intelj.repository.ReservationRepository;
import javax.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static java.security.KeyRep.Type.SECRET;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private BookMapper mapper;

    private static final String SECRET = "KH";

    public BookDTO saveBook(BookDTO book) {
        Book bookDetails = mapper.toEntity(book);
        Book savedBook = bookRepository.save(bookDetails);

        return mapper.toDTO(savedBook);
    }

    public List<Book> findAllBooks() {

        return bookRepository.findAll();
    }
    public Book findBooksById(Long id) {

        return bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException("Book not found with Id: "+id));
    }

    public List<Book> findBooksByGenre(Long genreId) {

        return bookRepository.findBooksByGenre(genreId);
    }
    public List<Book> findBooksByAuthor(Long authorId){
        return null;
    }
    public List<Book> findBooksByRating(Double Rating){
        return null;
    }

    public BookDTO updateBook(Long id, BookDTO updatedBook) {
        Book existingBook = bookRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Book not found"));
    // Here you can update the existing book with the updated book's fields
       Book bookSearch = mapper.toEntity(updatedBook);

       existingBook.setTitle(updatedBook.getTitle());
       existingBook.setAuthor(bookSearch.getAuthor());
        Book updatedEntity = bookRepository.save(existingBook);
        return mapper.toDTO(updatedEntity);
    }

    public void deleteBook(Long id){
        bookRepository.deleteById(id);
    }
    public List<Book> searchBooks(String keyword) {
        if (keyword == null || keyword.isEmpty()) {
            return bookRepository.findAll();
        }
        return bookRepository.search(keyword);
    }

    public boolean validateJwtToken(String jwtToken) {
        // validate the JWT token
        // this can include decoding the JWT and checking the issuer, expiration etc
        /**
         * Add the dependency for Autho
         * create a secret key
         * decode the JWT and check the expiry date
         * checking the issuer
         */

        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);

            // verify the token
            DecodedJWT jwt = JWT.require(algorithm)
                    .build()
                    .verify(jwtToken);

            // check the expiry
            Date expiresAt = jwt.getExpiresAt();
            if (expiresAt.before(new Date())) {
                return false;
            }

            // check the issuer
            Claim issuerClaim = jwt.getClaim("role");
            if (issuerClaim == null) {
                return false;
            }


        }catch(JWTVerificationException e){
        return false;
    }
    return true;
}

public boolean reserveBook(Long bookId, Long userId){
    Book book = bookRepository.findById(bookId).orElse(null);

    if (book!= null && BookStatus.AVAILABLE.equals(book.getStatus())){
        book.setStatus(BookStatus.RESERVED);
        bookRepository.save(book);

        Reservation reservation = new Reservation();
        reservation.setBookId(bookId);
        reservation.setUserId(userId);
        reservation.setReservationDate(new Date());
        reservationRepository.save(reservation);
        return true;
    }
    return false;
    }
}


