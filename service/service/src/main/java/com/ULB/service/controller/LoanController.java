package com.ULB.service.controller;

import com.ULB.service.dto.LoanDTO;
import com.ULB.service.entity.LoanRecord;
import com.ULB.service.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loan")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @PostMapping("/issueBook")
    public ResponseEntity<?> issueBook(@RequestBody LoanDTO loanDTO) {
        try {
            return loanService.issue(loanDTO);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/users/{userId}")
    public List<LoanRecord> getLoanRecordsForUser(@PathVariable Long userId) {
        return loanService.getLoanRecords(userId);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getLoanRecordForBook(@PathVariable Long userId, @RequestParam(required = true) Long bookId) {
        try {
            return loanService.getLoanRecord(bookId);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
