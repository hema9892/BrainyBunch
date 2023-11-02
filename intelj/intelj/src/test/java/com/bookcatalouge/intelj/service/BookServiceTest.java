package com.bookcatalouge.intelj.service;

import com.bookcatalouge.intelj.entity.Book;
import com.bookcatalouge.intelj.entity.BookStatus;
import com.bookcatalouge.intelj.entity.Reservation;
import com.bookcatalouge.intelj.repository.BookRepository;
import com.bookcatalouge.intelj.repository.ReservationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)

class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private ReservationRepository reservationRepository;
    @InjectMocks
    private BookService bookService;

    @Test
public void testReserveBookSuccess(){
            Long bookId = 1L;
            Long userId = 2l;
            Date reservationDate = new Date();
        Book book = new Book();
        book.setId(bookId);
        book.setStatus(BookStatus.AVAILABLE);

        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));


        when(reservationRepository.save(Mockito.any(Reservation.class))).thenReturn(new Reservation());


        boolean result = bookService.reserveBook(bookId, userId);
        assertTrue(result);


        assertEquals(BookStatus.RESERVED, book.getStatus());


        Mockito.verify(reservationRepository, Mockito.times(1)).save(Mockito.any(Reservation.class));
    }
    @Test
    public void testReservedBookBookNotFound() {
        Long bookId = 1L;
        Long userId = 2L;


        when(bookRepository.findById(bookId)).thenReturn(Optional.empty());


        boolean result = bookService.reserveBook(bookId, userId);
        assertFalse(result);
    }


    @Test
    public void testReserveBookBookNotAvailable() {
        Long bookId = 1L;
        Long userId = 2L;


        Book book = new Book();
        book.setId(bookId);
        book.setStatus(BookStatus.BORROWED);


        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));


        boolean result = bookService.reserveBook(bookId, userId);
        assertFalse(result);
        assertEquals(BookStatus.BORROWED, book.getStatus());

        verify(bookRepository, never()).save(book);
        verify(reservationRepository, never()).save(any(Reservation.class));

    }

    @Test
    public void testReserveBookBookAlreadyReserved() {
        Long bookId = 1L;
        Long userId = 2L;

        Book book = new Book();
        book.setId(bookId);
        book.setStatus(BookStatus.RESERVED);

        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));
        boolean result = bookService.reserveBook(bookId, userId);
        assertFalse(result);
        Mockito.verify(bookRepository, Mockito.never()).save(Mockito.any(Book.class));

        // Verify that no reservation is created
        Mockito.verify(reservationRepository, Mockito.never()).save(Mockito.any(Reservation.class));
    }

    @Test
    public void testReserveBookInternalServerError() {
        Long bookId = 2L;
        Long userId = 3L;

        when(bookRepository.findById(bookId)).thenReturn(new RuntimeException("Internal Server errpr"));

       boolean result = bookService.reserveBook(bookId, userId);
       assertFalse(result);

        Mockito.verify(bookRepository, Mockito.never()).save(Mockito.any(Book.class));

    }

    @Test
    public void testReserveBook_emptybookId(){
        Long bookId = null;
        Long userId = 3L;

        when(bookRepository.findById(bookId)).thenReturn(null);

        boolean result = bookService.reserveBook(bookId, userId);
        assertFalse(result);
    }

    @Test
    public void testReserveBook_emptyUserId(){
        Long bookId = 2L;
        Long userId = null;
        when(bookRepository.findById(bookId)).thenReturn(null);

        Boolean result = bookService.reserveBook(bookId, userId);
        assertFalse(result);

    }
    }