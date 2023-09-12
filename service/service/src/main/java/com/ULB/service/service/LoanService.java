package com.ULB.service.service;

import com.ULB.service.entity.Book;
import com.ULB.service.entity.LoanRecord;
import com.ULB.service.entity.User;
import com.ULB.service.dto.LoanDTO;
import com.ULB.service.repository.BookRepository;
import com.ULB.service.repository.LoanRepository;
import com.ULB.service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.List;

import static com.ULB.service.entity.BookStatus.AVAILABLE;
import static com.ULB.service.entity.BookStatus.LOANED;

@Service
public class LoanService {

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;




    public List<LoanRecord> getLoanRecords(Long userId) {
        List<LoanRecord> loanRecords = loanRepository.findByUserId(userId);
        return loanRecords;
    }
        public ResponseEntity<?> getLoanRecord(Long bookId) {
      LoanRecord loanRecords = loanRepository.findByBookId(bookId);
      return new ResponseEntity<>(loanRecords.getDueDate(), HttpStatus.OK);
        }

     public ResponseEntity<?> issue(LoanDTO loanDTO){
            User user = userRepository.findById(loanDTO.getUserId()).orElse(null);
            List<LoanRecord> loanRecords = loanRepository.findByUserId(user.getId());
            int limit = 4;
            Book book = bookRepository.findById(loanDTO.getBookId()).orElse(null);
            if (loanRecords.size() < limit && AVAILABLE == book.getStatus()) {
                LoanRecord loanRecord = LoanRecord.builder()
                        .userId(user.getId())
                        .bookId(loanDTO.getBookId())
                        .issueDate(LocalDate.now())
                        .dueDate(LocalDate.now().plusDays(14))
                        .build();
                book.setTitle(book.getTitle());
                book.setISBN(book.getISBN());
                book.setStatus(LOANED);
                bookRepository.save(book);
                return ResponseEntity.ok(loanRepository.save(loanRecord));
            }
            return new ResponseEntity<>(new IllegalArgumentException("book is not available for loan or Exc"), HttpStatus.UNAUTHORIZED);


        }

    }


